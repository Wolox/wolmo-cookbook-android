package ar.com.wolox.android.cookbook.camerax

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class PicturePresenter @Inject constructor() : BasePresenter<PictureView>() {

    fun onInit(image: String) = view.showImage(image)
}
