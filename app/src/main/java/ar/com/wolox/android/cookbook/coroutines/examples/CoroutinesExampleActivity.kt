package ar.com.wolox.android.cookbook.coroutines.examples

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class CoroutinesExampleActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, CoroutinesExampleFragment.newInstance())

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, CoroutinesExampleActivity::class.java))
    }
}