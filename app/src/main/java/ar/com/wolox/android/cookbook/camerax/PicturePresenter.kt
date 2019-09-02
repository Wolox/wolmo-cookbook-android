package ar.com.wolox.android.cookbook.camerax

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class PicturePresenter @Inject constructor() : BasePresenter<PictureView>() {

    /** Invoked on view init with the [image] requested to show. */
    fun onInit(image: String) = view.showImage(image)

    /** Invoked on close button clicked. */
    fun onCloseButtonClicked() = view.finish()
}
