package ar.com.wolox.android.cookbook.motionLayout.animations

import android.app.AlertDialog
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.motionLayout.IMotionView
import ar.com.wolox.android.cookbook.motionLayout.MotionPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class AnimationsFragment @Inject constructor() : WolmoFragment<MotionPresenter>(), IMotionView {

    override fun layout(): Int = R.layout.fragment_motion_layout_animations

    override fun init() {
        val alertDialog = AlertDialog.Builder(requireActivity())
                .setMessage(R.string.motion_layout_animations_alert_dialog)
                .setPositiveButton(R.string.motion_layout_animations_alert_dialog_positive_button) { _, _ -> }
                .show()
    }

    companion object {
        fun newInstance() = AnimationsFragment()
    }
}