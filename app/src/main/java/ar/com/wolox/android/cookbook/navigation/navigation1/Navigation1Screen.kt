package ar.com.wolox.android.cookbook.navigation.navigation1

import android.content.Context
import ar.com.wolox.android.cookbook.navigation.navigation2.Navigation2Screen
import ar.com.wolox.android.cookbook.navigation.newNavigation.NewNavigationScreen
import com.wealthfront.magellan.Screen

class Navigation1Screen : Screen<Navigation1View>() {

    override fun createView(context: Context?): Navigation1View = Navigation1View(context!!)

    fun goToNavigation2Screen() = navigator.goTo(Navigation2Screen())

    fun goToNewNavigationScreen() = navigator.goTo(NewNavigationScreen())
}
