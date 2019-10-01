package ar.com.wolox.android.cookbook.motionLayout.viewPager

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.motionLayout.IMotionView
import ar.com.wolox.android.cookbook.motionLayout.MotionPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class ViewPagerLottieFragment @Inject constructor() : WolmoFragment<MotionPresenter>(), IMotionView {

    override fun layout(): Int = R.layout.fragment_motion_layout_lottie

    override fun init() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}