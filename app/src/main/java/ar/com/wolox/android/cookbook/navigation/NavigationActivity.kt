package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NavigationActivity : WolmoActivity() {

    override fun layout() = R.layout.activity_base

    override fun init() {}

//    override fun onSupportNavigateUp() =
//                Navigation.findNavController(this, R.id.vActivityBaseContent).navigateUp()
}
