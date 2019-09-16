package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.CookbookApplication
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.screens.Navigation1Screen
import ar.com.wolox.android.cookbook.navigation.screens.NavigationActivityScreen
import ar.com.wolox.android.cookbook.navigation.screens.NavigationAnimationTransitionProvider
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.annotations.RegisterScreen

@RegisterScreen(NavigationActivityScreen::class)
class NavigationActivity : WolmoActivity() {

    private val navigationContextBinder = CookbookApplication.getNavigationContextBinder()
    private val navigator = CookbookApplication.getNavigator()

    override fun layout() = R.layout.activity_base

    override fun init() {
        navigator.reset(Navigation1Screen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val navigationContext = NavigationContext.Builder(this)
                .containerId(R.id.vActivityBaseContent)
                .transitionAnimationProvider(NavigationAnimationTransitionProvider())
                .build()
        navigationContextBinder.bind(navigationContext)
    }

    override fun onPause() {
        super.onPause()
        navigationContextBinder.unbind(this)
    }
}
