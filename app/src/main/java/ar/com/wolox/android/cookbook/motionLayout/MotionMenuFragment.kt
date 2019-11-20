package ar.com.wolox.android.cookbook.motionLayout

import android.support.v4.app.Fragment
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.motionLayout.animations.AnimationsFragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.ViewPagerFragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.ViewPagerLottieFragment
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_motion_layout_menu.*

class MotionMenuFragment : WolmoFragment<MotionPresenter>(), IMotionView {

    override fun layout(): Int = R.layout.fragment_motion_layout_menu

    override fun init() {}

    override fun setListeners() {
        bViewPagerMotion.setOnClickListener {
            replaceFragment(R.id.vActivityBaseContent, ViewPagerFragment.newInstance())
        }
        bAnimationsMotion.setOnClickListener {
            replaceFragment(R.id.vActivityBaseContent, AnimationsFragment.newInstance())
        }
        bViewPagerLottie.setOnClickListener {
            replaceFragment(R.id.vActivityBaseContent, ViewPagerLottieFragment.newInstance())
        }
    }

    private fun replaceFragment(resId: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(resId, fragment)
        fragmentTransaction?.commit()
    }
}