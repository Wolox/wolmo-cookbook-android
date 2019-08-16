package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class CoroutinesRecipeActivity : WolmoActivity() {

    val fragment = CoroutinesRecipeFragment()

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, fragment)
}