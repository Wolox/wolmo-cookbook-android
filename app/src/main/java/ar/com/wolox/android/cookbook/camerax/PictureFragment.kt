package ar.com.wolox.android.cookbook.camerax

import android.net.Uri
import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_picture.*
import java.io.File

class PictureFragment : WolmoFragment<PicturePresenter>(), PictureView {

    override fun layout() = R.layout.fragment_picture

    override fun handleArguments(arguments: Bundle?) = arguments?.get(PICTURE_URI) != null

    override fun init() {
        presenter.onInit(arguments!!.getString(PICTURE_URI)!!)
    }

    override fun setListeners() {
        vCloseImageButton.setOnClickListener { presenter.onCloseButtonClicked() }
    }

    override fun showImage(uriString: String) = vImage.setImageURI(Uri.fromFile(File(uriString)), null)

    override fun finish() = requireActivity().finish()

    companion object {

        const val PICTURE_URI = "PICTURE_URI"

        fun newInstance(arguments: Bundle) = PictureFragment().apply {
            this.arguments = arguments
        }
    }
}

interface PictureView {

    /** Show image. */
    fun showImage(uriString: String)

    /** Finish the view. */
    fun finish()
}