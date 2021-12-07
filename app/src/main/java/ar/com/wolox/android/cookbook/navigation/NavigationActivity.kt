package ar.com.wolox.android.cookbook.navigation

import androidx.fragment.app.Fragment
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.fragment.IWolmoFragment

class NavigationActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base_navigation

    override fun init() {}

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.vActivityBaseContent)!!
        val fragments = navHostFragment.childFragmentManager.fragments
        val var2 = fragments.iterator()

        while (var2.hasNext()) {
            val childFragment = var2.next() as Fragment
            if (childFragment is IWolmoFragment && childFragment.isVisible && (childFragment as IWolmoFragment).onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
}
