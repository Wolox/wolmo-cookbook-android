package ar.com.wolox.android.cookbook.coroutines.football

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class FootballActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, FootballFragment.newInstance())

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, FootballActivity::class.java))
    }
}