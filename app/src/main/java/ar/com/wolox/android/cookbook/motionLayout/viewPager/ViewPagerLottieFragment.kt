package ar.com.wolox.android.cookbook.motionLayout.viewPager

import android.os.Handler
import ar.com.wolox.android.cookbook.R
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import ar.com.wolox.android.cookbook.motionLayout.IMotionView
import ar.com.wolox.android.cookbook.motionLayout.MotionPresenter
import ar.com.wolox.android.cookbook.motionLayout.viewPager.page1.Page1Fragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_motion_layout_lottie.*
import javax.inject.Inject

class ViewPagerLottieFragment @Inject constructor() : WolmoFragment<MotionPresenter>(), IMotionView {

    @Inject lateinit var page1Fragment: Page1Fragment
    @Inject lateinit var page2Fragment: Page1Fragment
    @Inject lateinit var page3Fragment: Page1Fragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_motion_layout_lottie

    override fun init() {
        initViewPagerWithLottie()
        setUpViewPagerWithLottie()
    }

    private fun initViewPagerWithLottie() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair(page1Fragment, PAGE_ONE),
                Pair(page2Fragment, PAGE_TWO),
                Pair(page3Fragment, PAGE_THREE)
        )
        vViewPagerLottie.adapter = fragmentPagerAdapter
        vTabsLottie.setupWithViewPager(vViewPagerLottie)
    }

    private fun setUpViewPagerWithLottie() {
        vViewPagerLottie.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Handler().postDelayed({
                    /* change motion layout progress according to the viewPager position */
                    vMotionLayoutLottie.progress = (position + positionOffset) / (fragmentPagerAdapter.count - 1)
                }, 100)
            }

            override fun onPageSelected(position: Int) {}
        })
    }

    companion object {
        fun newInstance() = ViewPagerLottieFragment()

        const val PAGE_ONE = "Page 1"
        const val PAGE_TWO = "Page 2"
        const val PAGE_THREE = "Page 3"
    }
}