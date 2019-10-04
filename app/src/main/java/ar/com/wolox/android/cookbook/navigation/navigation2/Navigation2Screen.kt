package ar.com.wolox.android.cookbook.navigation.navigation2

import android.content.Context
import com.wealthfront.magellan.Screen

class Navigation2Screen : Screen<Navigation2View>() {

    override fun createView(context: Context?): Navigation2View = Navigation2View(context)
}
