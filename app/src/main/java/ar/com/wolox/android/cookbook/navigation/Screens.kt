package ar.com.wolox.android.cookbook.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ar.com.wolox.android.cookbook.navigation.one.Navigation1Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class Navigation2Screen : SupportAppScreen() {

        override fun getFragment(): Navigation2Fragment = Navigation2Fragment()
    }

    class NavigationNewScreen : SupportAppScreen() {

        override fun getActivityIntent(context: Context?): Intent = Intent(context, NewNavigationActivity::class.java)
    }

    class Navigation1Screen : SupportAppScreen() {

        override fun getFragment(): Navigation1Fragment = Navigation1Fragment()
    }

    class Navigation3Screen : SupportAppScreen() {

        override fun getFragment(): Navigation3Fragment = Navigation3Fragment()
    }

    class Navigation4Screen(private val bundle: Bundle) : SupportAppScreen() {
        override fun getFragment(): Navigation4Fragment = Navigation4Fragment().apply {
            arguments = bundle
        }
    }
}
