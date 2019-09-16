package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.Navigation2Screen
import ar.com.wolox.android.cookbook.navigation.screens.Navigation3Screen
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation2.*
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(Navigation2Screen::class)
class Navigation2Fragment : WolmoFragment<BasePresenter<Any>>() {

    private val navigator = CookbookApplication.getNavigator()

    override fun layout(): Int = R.layout.fragment_navigation2

    override fun init() {

        // Going back programatically
        fragment_navigation2_to1_button.setOnClickListener {
            navigator.goBack()
        }

        // Fragment2B to Fragment3 / When clicking back on Fragment3 it will return to Fragment1
        fragment_navigation2_to3_button.setOnClickListener {
            navigator.goForward(Navigation3Screen())
        }
    }

    override fun onBackPressed(): Boolean {
        navigator.goBack()
        return true
    }
}
