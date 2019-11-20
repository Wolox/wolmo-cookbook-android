package ar.com.wolox.android.cookbook.motionLayout.viewPager

import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.motionLayout.MotionPresenter
import ar.com.wolox.android.cookbook.motionLayout.viewPager.page1.Page1Fragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.page2.Page2Fragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_viewpagermotion.*
import kotlinx.android.synthetic.main.motionlayout_viewpager.*
import javax.inject.Inject

class ViewPagerFragment @Inject constructor() : WolmoFragment<MotionPresenter>() {

    @Inject lateinit var page2Fragment: Page2Fragment
    @Inject lateinit var page1Fragment: Page1Fragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter
    private var numberOfPages: Int = 0

    companion object {
        fun newInstance(): ViewPagerFragment {
            return ViewPagerFragment()
        }

        const val PAGE1_NAME = "page1"
        const val PAGE2_NAME = "page2"
    }

    override fun layout(): Int = R.layout.fragment_viewpagermotion

    override fun init() {
        initViewPager()
    }

    private fun initViewPager() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
                addFragments(
                    Pair<Fragment, String>(page1Fragment, PAGE1_NAME),
                    Pair<Fragment, String>(page2Fragment, PAGE2_NAME)
            )
        }
        vViewPagerMotion.adapter = fragmentPagerAdapter
        vHomeTabsMotion.setupWithViewPager(vViewPagerMotion)
        numberOfPages = 2
        setUpViewPagerMotionListener()
    }

    private fun setUpViewPagerMotionListener() {
        vViewPagerMotion.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Handler().postDelayed({
                    /* change motion layout progress according to the viewPager position */
                    vMotionLayout.progress = (position + positionOffset) / (numberOfPages - 1)
                }, 100)
            }

            override fun onPageSelected(position: Int) {}
        })
    }
}