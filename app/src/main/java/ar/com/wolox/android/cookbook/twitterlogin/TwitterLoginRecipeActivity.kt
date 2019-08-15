package ar.com.wolox.android.cookbook.twitterlogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class TwitterLoginRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, FacebookLoginRecipeFragment())
}