package ar.com.wolox.android.cookbook.camerax

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.io.File
import javax.inject.Inject

class PicturePresenter @Inject constructor() : BasePresenter<PictureView>() {

    lateinit var image: String

    /** Invoked on view init with the [image] requested to show. */
    fun onInit(image: String) {
        this.image = image
        view.showImage(image)
    }

    /** Invoked on close button clicked. */
    fun onCloseButtonClicked() {
        File(image).delete()
        view.finish()
    }
}
