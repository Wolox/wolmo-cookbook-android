package ar.com.wolox.android.cookbook.notifications

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class NotificationActivity : WolmoActivity() {

    @Inject lateinit var mToastFactory: ToastFactory

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, NotificationFragment())

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mToastFactory.show(R.string.notifications_general_channel)
    }
}