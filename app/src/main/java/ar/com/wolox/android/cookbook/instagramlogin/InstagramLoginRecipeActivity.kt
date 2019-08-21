package ar.com.wolox.android.cookbook.instagramlogin

import android.content.Intent
import android.util.Log
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class InstagramLoginRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, InstagramLoginRecipeFragment())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("IG", "activityResult")
    }
}