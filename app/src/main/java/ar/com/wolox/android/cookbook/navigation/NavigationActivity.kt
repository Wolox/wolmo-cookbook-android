package ar.com.wolox.android.cookbook.navigation

import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.support.SingleActivity
import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.navigation1.Navigation1Screen

class NavigationActivity : SingleActivity() {

    override fun createNavigator(): Navigator = Navigator.withRoot(Navigation1Screen()).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_magellan)
    }
}
