package ar.com.wolox.android.cookbook.navigation

import android.support.v4.app.Fragment
import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.R.id.action_navigation3Fragment_to_navigation1Fragment2
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import kotlinx.android.synthetic.main.activity_base.*

class NavigationActivity : WolmoActivity() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        // Setting forward animations
        NavigationUtils.setForwardAnimationOptions(R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right)

        // Setting backward/popup animations
        NavigationUtils.setBackwardAnimationOptions(R.anim.slide_in_left)
    }

    override fun onBackPressed() {
        // Back is pressed on Fragment3 it will navigate to Fragment1 and popup the backstack until Fragment1 inclusive
        if (currentFragment is Navigation3Fragment) {
            Navigation.findNavController(this, R.id.vActivityBaseContent).navigate(action_navigation3Fragment_to_navigation1Fragment2, null,
                    NavigationUtils.getBackwardOptions(R.id.navigation1Fragment, true))
        } else if (currentFragment is Navigation4Fragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    val currentFragment: Fragment?
        get() = vActivityBaseContent.childFragmentManager.findFragmentById(R.id.vActivityBaseContent)
}
