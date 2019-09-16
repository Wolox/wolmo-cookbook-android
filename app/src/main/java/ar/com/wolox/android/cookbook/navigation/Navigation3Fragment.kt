package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.Navigation3Screen
import ar.com.wolox.android.cookbook.navigation.screens.Navigation4Screen
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation3.*
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(Navigation3Screen::class)
class Navigation3Fragment : WolmoFragment<BasePresenter<Any>>() {

    private val navigator = CookbookApplication.getNavigator()

    override fun layout(): Int = R.layout.fragment_navigation3

    override fun init() {

        // Fragment3 to Fragment4 with arguments
        fragment_navigation3_to4_button.setOnClickListener {
            navigator.goForward(Navigation4Screen(fragment3_edit_text.text.toString()))
        }
    }

    override fun onBackPressed(): Boolean {
        // Going back to Navigation1Fragment and popup backstack
        navigator.goBack()
        return true
    }
}
