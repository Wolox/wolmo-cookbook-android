package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class GoogleLoginActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, GoogleLoginFragment())
    }
}