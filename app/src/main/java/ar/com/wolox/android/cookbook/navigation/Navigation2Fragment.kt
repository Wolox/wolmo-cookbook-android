package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.one.NavigationPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_navigation2.*

class Navigation2Fragment : WolmoFragment<NavigationPresenter>() {

    override fun layout(): Int = R.layout.fragment_navigation2

    override fun init() {

        // Going back programatically
        fragment_navigation2_to1_button.setOnClickListener {
            presenter.onGoBackTo1()
        }

        // Fragment2B to Fragment3 / When clicking back on Fragment3 it will return to Fragment1
        fragment_navigation2_to3_button.setOnClickListener {
            presenter.onGoToNewRootChain3()
        }
    }
}
