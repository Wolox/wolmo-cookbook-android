package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.NavigationAnimationTransitionProvider
import ar.com.wolox.android.cookbook.navigation.screens.NewNavigationActivityScreen
import ar.com.wolox.android.cookbook.navigation.screens.NewNavigationScreen
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(NewNavigationActivityScreen::class)
class NewNavigationActivity : WolmoActivity() {

    private val navigator = CookbookApplication.getNavigator()
    private val navigationContextBinder = CookbookApplication.getNavigationContextBinder()

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        navigator.reset(NewNavigationScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val navigationContext = NavigationContext.Builder(this)
                .containerId(R.id.vActivityBaseContent)
                .transitionAnimationProvider(NavigationAnimationTransitionProvider())
                .build()
        navigationContextBinder.bind(navigationContext)
    }
}
