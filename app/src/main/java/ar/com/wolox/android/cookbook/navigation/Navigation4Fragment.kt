package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class Navigation4Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation4

    override fun init() {
    }
}
