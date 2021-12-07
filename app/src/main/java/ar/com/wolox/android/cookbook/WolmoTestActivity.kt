package ar.com.wolox.android.cookbook

import androidx.fragment.app.Fragment
import ar.com.wolox.android.cookbook.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class WolmoTestActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {}

    fun initFragment(fragment: Fragment) {
        replaceFragment(R.id.vActivityBaseContent, fragment)
    }
}