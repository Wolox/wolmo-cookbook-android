package ar.com.wolox.android.cookbook.navigation.newNavigation

import android.content.Context
import com.wealthfront.magellan.Screen

class NewNavigationScreen : Screen<NewNavigationView>() {

    override fun createView(context: Context?): NewNavigationView = NewNavigationView(context!!)
}
