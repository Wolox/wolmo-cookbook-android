package ar.com.wolox.android.cookbook.camerax

import android.content.Context
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import java.io.File
import javax.inject.Inject

@ApplicationScope
class FilesHelper @Inject constructor(context: Context) {

    private var cacheFolder: String = getTmpDirectory(context).absolutePath

    private fun getTmpDirectory(context: Context) = File(context.cacheDir, IMAGES_FOLDER).apply {
        if (!exists()) {
            mkdir()
        }
    }

    /** Returns a generated picture file name on cache. */
    fun getNewCachePictureName() = cacheFolder + FILE_FORMAT.format(System.nanoTime(), PNG_EXTENSION)

    companion object {
        private const val IMAGES_FOLDER = "Cookbook"
        private const val FILE_FORMAT = "/%s%s"
        private const val PNG_EXTENSION = ".png"
    }
}