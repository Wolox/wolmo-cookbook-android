package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.Navigation1Screen
import ar.com.wolox.android.cookbook.navigation.screens.Navigation2Screen
import ar.com.wolox.android.cookbook.navigation.screens.NewNavigationActivityScreen
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation1.*
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(Navigation1Screen::class)
class Navigation1Fragment : WolmoFragment<BasePresenter<Any>>() {

    private val navigator = CookbookApplication.getNavigator()

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {

        // Fragment1 to Fragment2
        fragment_navigation1__to2_button.setOnClickListener {
            navigator.goForward(Navigation2Screen())
        }

        // Fragment1 to NewActivity
        fragment_navigation1__toNewActivity.setOnClickListener {
            navigator.goForward(NewNavigationActivityScreen())
        }
    }
}
