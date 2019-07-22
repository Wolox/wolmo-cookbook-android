package ar.com.wolox.android.cookbook.motionLayout.viewPager.page2

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class Page2Fragment @Inject constructor() : WolmoFragment<Page2Presenter>(), IPage2View {

    override fun init() {
    }

    override fun layout(): Int = R.layout.fragment_page2
}