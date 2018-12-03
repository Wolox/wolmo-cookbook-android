package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class NewNavigationFragment : WolmoFragment<BasePresenter<Any>>() {
    override fun init() {
    }

    override fun layout(): Int = R.layout.fragment_new_navigation
}
