#!/usr/bin/python
import os, glob, hashlib, pickle, argparse, shutil, ntpath
import os, glob, hashlib, pickle, argparse, shutil, multiprocessing, signal, sys
from multiprocessing import Pool
from functools import partial

######################### Classes ##############################
class AndroidDensity:
    def __init__(self, name, path, scaleFactor):
        self.name = name
        self.path = path
        self.scaleFactor = scaleFactor

class IosDensity:
    def __init__(self, name, suffix, scaleFactor):
        self.name = name
        self.suffix = suffix
        self.scaleFactor = scaleFactor

class Colors:
    PURPLE = '\033[95m'
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'
################################################################

################# Directories configuration ####################
dirRoot = "./"
dirRaw = dirRoot + "raw/"
dirAssets = dirRoot + "drawables/"

# ScaleFactor with origin in XXXHDPI density. Source: http://jennift.com/dpical.html
androidDensities = [
AndroidDensity("HDPI", "drawable-hdpi/", 0.375),
AndroidDensity("X-HDPI", "drawable-xhdpi/", 0.5),
AndroidDensity("XX-HDPI", "drawable-xxhdpi/", 0.75),
AndroidDensity("XXX-HDPI", "drawable-xxxhdpi/", 1.0)
]

# ScaleFactor with origin in @3X density.
iosDensities = [
IosDensity("@1X", "", 0.333333),
IosDensity("@2X", "@2X", 0.666666),
IosDensity("@3X", "@3X", 1.0)
]
################################################################

# Constants
STORAGE_FILE_NAME = ".warp_storage"
TARGET_ANDROID = "android"
TARGET_IOS = "ios"

# Variables with default values
poolThreads = multiprocessing.cpu_count() + 1
upToDateFiles = []
deletedFiles = []
newFiles = []
modifiedFiles = []
targetPlatform = ""
shouldCleanProject = False
shouldRunSilently = False
versionName = "1.0.1"

# Script entry point
def main():
    parseCommandLineOptions()
    greet()
    setUpPathVariables()

    if shouldCleanProject or shouldForceCleanProject:
        cleanProject()
    else:
        makeRequiredDirectories()
        classifyRawFiles(upToDateFiles, deletedFiles, newFiles, modifiedFiles)
        processUpToDateAssets(upToDateFiles)
        processNewAssets(newFiles)
        processModifiedAssets(modifiedFiles)
        processDeletedAssets(deletedFiles)

    goodbye()

# Parse command line options and store them in variables
def parseCommandLineOptions():
    parser = argparse.ArgumentParser(description="Seamless mobile assets management")

    baseGroup = parser.add_argument_group('Basic usage')
    baseGroup.add_argument("-t", "--target",
    dest="target",
    required=True,
    choices=[TARGET_ANDROID, TARGET_IOS],
    help="specifies the platform where the assets will be used",
    metavar=TARGET_ANDROID +"/" + TARGET_IOS)
    baseGroup.add_argument("-i", "--input",
    dest="input",
    help="directory where the raw assets are located",
    metavar="\"raw/assets/path\"")
    baseGroup.add_argument("-o", "--output",
    dest="output",
    help="directory where the processed assets will be placed",
    metavar="\"proccesed/assets/path\"")
    baseGroup.add_argument("-v", "--version",
    action='version',
    version='%(prog)s ' + versionName)
    baseGroup.add_argument("-T", "--threads",
    dest="threads",
    help="number of threads to use while processing the assets",
    metavar="N",
    default=multiprocessing.cpu_count() + 1,
    type=int)

    buildGroup = parser.add_argument_group('Processing options')
    buildGroup.add_argument("-c", "--clean",
    action="store_true",
    default=False,
    dest="clean",
    help="remove every generated asset")
    buildGroup.add_argument("-f", "--force-clean",
    action="store_true",
    default=False,
    dest="force_clean",
    help="forces the removal of the output folder")

    uiGroup = parser.add_argument_group('UI')
    uiGroup.add_argument("-s", "--silent",
    action="store_true",
    default=False,
    dest="silent",
    help="doesn't show the welcome message")

    # Save parsed options as global variables
    global targetPlatform
    global dirRaw
    global dirAssets
    global shouldCleanProject
    global shouldForceCleanProject
    global shouldRunSilently
    global poolThreads

    args = parser.parse_args()
    targetPlatform = args.target
    if args.input: dirRaw = args.input
    if args.output: dirAssets = args.output
    shouldCleanProject = args.clean
    shouldForceCleanProject = args.force_clean
    shouldRunSilently = args.silent
    poolThreads = args.threads if args.threads > 0 else 1

# Greet
def greet():
    logo = [
    "                                      ",
    "    **********************************",
    "    *  _       _____    ____  ____   *",
    "    * | |     / /   |  / __ \/ __ \\  *",
    "    * | | /| / / /| | / /_/ / /_/ /  *",
    "    * | |/ |/ / ___ |/ _, _/ ____/   *",
    "    * |__/|__/_/  |_/_/ |_/_/        *",
    "    *                                *",
    "    *  Wolox Assets Rapid Processor  *",
    "    **********************************",
    "                 v."+ versionName +"                   ",
    "                                      "
    ]
    if not shouldRunSilently:
        for line in logo:
            print(Colors.PURPLE + line + Colors.ENDC)

# Adds neccesary PATH variables. Useful when running the script from a non
# user shell (like with Gradle in Android)
def setUpPathVariables():
    os.environ['PATH'] = os.environ['PATH'] + ":/usr/local/bin"

# Clears previously processed assets and the hash storage file
def cleanProject():
    print(Colors.YELLOW + "Cleaning previously processed assets..." + Colors.ENDC)
    # Dictionary of previously hashed files: <file path, MD5 hash>
    storedHashedFiles = loadHashedFiles()

    # Delete all the stored files
    for path, md5 in storedHashedFiles.iteritems():
        assetToClean = ntpath.basename(path)
        print(Colors.BLUE + "DELETING ASSET: " + assetToClean + Colors.ENDC)
        deleteAsset(assetToClean)

    # Remove generated density folders if empty
    for density in androidDensities:
        densityDir = dirAssets + density.path
        if os.path.exists(densityDir) and (os.listdir(densityDir) == [] or shouldForceCleanProject) :
            print(Colors.BLUE + "DELETING ASSET DIRECTORY: " + densityDir + Colors.ENDC)
            if shouldForceCleanProject:
                shutil.rmtree(densityDir)
            else :
                os.rmdir(densityDir)

    # Remove assets output folder if empty
    if os.path.exists(dirAssets) and os.listdir(dirAssets) == [] :
        print(Colors.BLUE + "DELETING EMPTY OUTPUT DIRECTORY: " + dirAssets + Colors.ENDC)
        os.rmdir(dirAssets)

    # Remove storage file
    if os.path.exists(dirRaw + STORAGE_FILE_NAME):
        os.remove(dirRaw + STORAGE_FILE_NAME)

    print(Colors.YELLOW + "Assets cleared" + Colors.ENDC)

# Make the required directories to process asssets if they doesn't exist already
def makeRequiredDirectories():
    # Make raw directory if needed
    if not os.path.exists(dirRaw):
        print("Making directory for raw assets: " + dirRaw)
        os.makedirs(dirRaw)

    # Make directories for Android processed assets
    if targetPlatform == TARGET_ANDROID:
        for density in androidDensities:
            if not os.path.exists(dirAssets + density.path):
                print("Making directory for Android assets: " + dirAssets + density.path)
                os.makedirs(dirAssets + density.path)

    # Make directories for iOS processed assets
    else:
        if not os.path.exists(dirAssets):
            print("Making directory for iOS assets:" + dirAssets)
            os.makedirs(dirAssets)


# Classify raw files into collections of up to date, deleted, new and modified files
def classifyRawFiles(upToDateFiles, deletedFiles, newFiles, modifiedFiles):
    # Dictionary of previously hashed files: <file path, MD5 hash>
    storedHashedFiles = loadHashedFiles()
    # Dictionary of newly hashed files and ready to compare for diff: <file path, MD5 hash>
    recentlyHashedFiles = hashRawFiles()

    saveHashedFiles(recentlyHashedFiles)

    # Classify files by comparing recent hashes with previously hased files
    for path, md5 in recentlyHashedFiles.iteritems():
        if path in storedHashedFiles:
            # CASE 1: The file is present and the hashes are the same (the file is the same)
            if md5 == recentlyHashedFiles[path]:
                upToDateFiles.append(path)
            # CASE 2: The file is present, but the hashes doesn't match (the file has been modified)
            else:
                modifiedFiles.append(path)

            del storedHashedFiles[path] # Removed the processed entry
        # CASE 3: The file isn't present on the previous hash dictionary, it must be a new file
        else:
            newFiles.append(path)

    # The leftovers in the previous hash dictionary must be the deleted files
    for path in storedHashedFiles:
        deletedFiles.append(path)

# Hash (MD5) files in the raw directory and return them as a dictionary <file path, MD5 hash>
def hashRawFiles():
    BLOCKSIZE = 65536
    hashedFiles = {}
    # Hash files in the raw directory
    for filePath in glob.glob(dirRaw + "*.png"):
        hasher = hashlib.md5()
        with open(filePath, 'rb') as fileToHash:
            buf = fileToHash.read(BLOCKSIZE)
            while len(buf) > 0:
                hasher.update(buf)
                buf = fileToHash.read(BLOCKSIZE)
        hashedFiles.update({filePath:hasher.hexdigest()})
    return hashedFiles

# Store a dictionary of files to Hash
def saveHashedFiles(filesToHash):
    with open(dirRaw + STORAGE_FILE_NAME, "wb") as hashStorage:
        pickle.dump(filesToHash, hashStorage, pickle.HIGHEST_PROTOCOL)

# Retrieve a dictionary of hashed files
def loadHashedFiles():
    try:
        with open(dirRaw + STORAGE_FILE_NAME, "rb") as hashStorage:
            return pickle.load(hashStorage)
    except IOError:
        return {}

# Process files that we found in a previous run by the script
def processUpToDateAssets(upToDateFiles):
    for path in upToDateFiles:
        print(Colors.BLUE + os.path.basename(path) + ": STATE > UP TO DATE" + Colors.ENDC)

# Execute a specific function in a pool of workers for every "argument" in mapArguments.
def mapInWorkers(function, mapArguments):
    pool = Pool(poolThreads)

    try:
        pool.map_async(function, mapArguments).get(0xFFFF)
        pool.close()
    except KeyboardInterrupt:
        print(Colors.RED + "Interrupted" + Colors.ENDC)
        pool.terminate()
        sys.exit(1)

# Process files that are new to the project
def processNewAssets(newFiles):
    processNew = partial(processRawPngAssetWithTitle, "{}: STATE > NEW")
    mapInWorkers(processNew, newFiles)

# Process files that were modified in the project
def processModifiedAssets(modifiedFiles):
    processModified = partial(processRawPngAssetWithTitle, "{}: STATE > UPDATED")
    mapInWorkers(processModified, modifiedFiles)

# Process files that were deleted from the project
def processDeletedAssets(deletedFiles):
    for path in deletedFiles:
        assetName = os.path.basename(path)
        print(Colors.BLUE + assetName + ": STATE > REMOVED" + Colors.ENDC)
        deleteAsset(assetName)

# Prints the title, replacing the keyword for the path basename, scale and compress the asset for every screen density
def processRawPngAssetWithTitle(title, rawAssetPath):
    print (Colors.BLUE + title.format(os.path.basename(rawAssetPath)) + Colors.ENDC)
    processRawPngAsset(rawAssetPath)

# Scale and compress the asset for every screen density
def processRawPngAsset(rawAssetPath):
    filename = os.path.basename(rawAssetPath)
    filenameAndExtension = os.path.splitext(filename) # "example.png" -> ["example", ".png"]

    # Process assets for Android (e.g: /drawable-xxhdpi/...)
    if targetPlatform == TARGET_ANDROID:
        for density in androidDensities:
            processedAssetPath = dirAssets + density.path + filename
            sendAssetToPngPipeline(rawAssetPath, density, processedAssetPath)

    # Process assets for iOS (e.g: ...@3X)
    else:
        for density in iosDensities:
            processedAssetPath = dirAssets + filenameAndExtension[0] + density.suffix + filenameAndExtension[1]
            sendAssetToPngPipeline(rawAssetPath, density, processedAssetPath)

    print(filename + ": Processed the asset for every screen density")

def sendAssetToPngPipeline(rawAssetPath, density, processedAssetPath):
    filename = os.path.basename(rawAssetPath)
    print("{0}: SCALING to {1}".format(filename, density.name))
    scaleImage(rawAssetPath, density.scaleFactor, processedAssetPath)
    print(filename + ": COMPRESSING for " + density.name)
    compressPNG(processedAssetPath)

# Scale the asset for a given screen density using FFMPEG
def scaleImage(inputPath, scaleFactor, outputPath):
    os.system("ffmpeg -loglevel error -y -i \"{0}\" -vf scale=iw*{1}:-1 \"{2}\"".format(inputPath, scaleFactor, outputPath))

# Compress a PNG asset using PNGQuant
def compressPNG(inputPath):
    os.system("pngquant \"{0}\" --force --ext .png".format(inputPath))

# Remove asset in every screen density
def deleteAsset(assetName):
    for density in androidDensities:
        if os.path.exists(dirAssets + density.path + assetName):
            os.remove(dirAssets + density.path + assetName)
            print(assetName + ": DELETED asset for " + density.name)

# Goodbye
def goodbye():
    print(Colors.GREEN + "WARP complete!" + Colors.ENDC)

# Main call
main()
