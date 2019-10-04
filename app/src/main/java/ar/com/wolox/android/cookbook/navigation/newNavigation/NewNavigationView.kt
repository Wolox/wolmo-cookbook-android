package ar.com.wolox.android.cookbook.navigation.newNavigation

import android.content.Context
import ar.com.wolox.android.cookbook.R
import com.wealthfront.magellan.BaseScreenView

class NewNavigationView(context: Context) : BaseScreenView<NewNavigationScreen>(context) {

    init {
        inflate(context, R.layout.fragment_new_navigation, this)
    }
}
