package ar.com.wolox.android.cookbook.navigation.navigation1

import android.content.Context
import ar.com.wolox.android.cookbook.R
import com.wealthfront.magellan.BaseScreenView
import kotlinx.android.synthetic.main.fragment_navigation1.view.*

class Navigation1View(context: Context) : BaseScreenView<Navigation1Screen>(context) {

    init {
        inflate(context, R.layout.fragment_navigation1, this)
        // Listeners
        fragment_navigation1__to2_button.setOnClickListener {
            screen.goToNavigation2Screen()
        }

        fragment_navigation1__toNewActivity.setOnClickListener {
            screen.goToNewNavigationScreen()
        }
    }
}
