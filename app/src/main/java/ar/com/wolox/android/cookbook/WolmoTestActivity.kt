package ar.com.wolox.android.cookbook

import android.support.v4.app.Fragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class WolmoTestActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {}

    fun initFragment(fragment: Fragment) {
        replaceFragment(R.id.vActivityBaseContent, fragment)
    }
}