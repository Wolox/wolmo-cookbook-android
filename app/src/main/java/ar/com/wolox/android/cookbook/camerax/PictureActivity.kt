package ar.com.wolox.android.cookbook.camerax

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class PictureActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, PictureFragment.newInstance(intent.extras!!))

    companion object {

        fun start(context: Context, picture: String) = context.startActivity(Intent(context, PictureActivity::class.java).apply {
            putExtra(PictureFragment.PICTURE_URI, picture)
        })
    }
}