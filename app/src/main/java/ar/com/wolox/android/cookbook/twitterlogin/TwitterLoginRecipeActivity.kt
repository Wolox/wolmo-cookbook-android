package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class TwitterLoginRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, TwitterLoginRecipeFragment())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.findFragmentById(R.id.vActivityBaseContent)
                ?.onActivityResult(requestCode, resultCode, data)
    }
}