package ar.com.wolox.android.cookbook.motionLayout.animations

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.motionLayout.IMotionView
import ar.com.wolox.android.cookbook.motionLayout.MotionPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class AnimationsFragment @Inject constructor() : WolmoFragment<MotionPresenter>(), IMotionView {

    override fun layout(): Int = R.layout.fragment_motion_layout_animations

    override fun init() {}

    companion object {
        fun newInstance() = AnimationsFragment()
    }
}