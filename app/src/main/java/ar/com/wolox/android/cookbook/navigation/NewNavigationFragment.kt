package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.NewNavigationScreen
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(NewNavigationScreen::class)
class NewNavigationFragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_new_navigation

    override fun init() {
    }
}
