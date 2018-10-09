import os, shutil

####################### Sanity Steps ###########################
def installGitHooks():
    if os.path.exists("./git-hooks/pre-commit"):
        print("Git hooks step -- Copying pre-commit")
        shutil.copy2("./git-hooks/pre-commit", "../../.git/hooks/pre-commit")

    if os.path.exists("./git-hooks/pre-push"):
        print("Git hooks step -- Copying pre-push")
        shutil.copy2("./git-hooks/pre-push", "../../.git/hooks/pre-push")

########################### Main ###############################
def main():
    print("Hooking sanity checks to Git")
    
    installGitHooks()

if __name__ == "__main__":
    main()