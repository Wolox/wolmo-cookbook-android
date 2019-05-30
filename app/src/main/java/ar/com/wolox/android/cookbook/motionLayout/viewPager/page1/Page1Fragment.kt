package ar.com.wolox.android.cookbook.motionLayout.viewPager.page1

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class Page1Fragment @Inject constructor() : WolmoFragment<Page1Presenter>(), IPage1View {

    override fun init() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun layout(): Int = R.layout.fragment_page1
}