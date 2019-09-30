package ar.com.wolox.android.cookbook.navigation

import android.content.Context
import android.content.Intent
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class Navigation2Screen : SupportAppScreen() {

        override fun getFragment(): Navigation2Fragment = Navigation2Fragment()
    }

    class NavigationNewScreen : SupportAppScreen() {

        override fun getActivityIntent(context: Context?): Intent = Intent(context, NewNavigationActivity::class.java)
    }
}
