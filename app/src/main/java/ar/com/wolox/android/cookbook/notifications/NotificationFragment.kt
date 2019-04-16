package ar.com.wolox.android.cookbook.notifications

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.notifications.helper.NotificationHelper
import ar.com.wolox.android.cookbook.notifications.model.BasicNotification
import ar.com.wolox.android.cookbook.notifications.model.NotificationAction
import ar.com.wolox.android.cookbook.notifications.model.PictureExpandableNotification
import ar.com.wolox.android.cookbook.notifications.model.TextExpandableNotification
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_notification.*

class NotificationFragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_notification

    override fun init() {}

    override fun setListeners() {
        vSimpleNotificationBtn.setOnClickListener { showBasicNotification() }
        vExpandableTextNotificationBtn.setOnClickListener { showTextExpandableNotification() }
        vBigPictureNotificationBtn.setOnClickListener { showPictureNotification() }
        vActionsNotificationBtn.setOnClickListener { showBasicNotificationWithActions() }
    }

    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }

    private fun showBasicNotification() {
        NotificationHelper.showNotification(requireContext(), 1, BasicNotification(
            null,
            getString(R.string.notifications_basic_title),
            getString(R.string.notifications_short_content),
            NotificationCompat.PRIORITY_MAX
        ))
    }

    private fun showTextExpandableNotification() {
        NotificationHelper.showNotification(requireContext(), 2, TextExpandableNotification(
            null,
            getString(R.string.notifications_basic_title),
            getString(R.string.notifications_short_content),
            NotificationCompat.PRIORITY_DEFAULT,
            getString(R.string.notifications_large_content)
        ))
    }

    private fun showPictureNotification() {
        NotificationHelper.showNotification(requireContext(), 3, PictureExpandableNotification(
            null,
            getString(R.string.notifications_basic_title),
            getString(R.string.notifications_short_content),
            NotificationCompat.PRIORITY_DEFAULT,
            getString(R.string.notifications_large_content),
            BitmapFactory.decodeResource(requireContext().resources, R.drawable.bg_cool_cat)
        ))
    }

    private fun showBasicNotificationWithActions() {
        val fbLoginIntent = PendingIntent.getActivity(requireContext(), 0, Intent(requireContext(), FacebookLoginRecipeActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val gLoginIntent = PendingIntent.getActivity(requireContext(), 0, Intent(requireContext(), GoogleLoginRecipeActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        NotificationHelper.showNotification(requireContext(), 1, BasicNotification(
            null,
            getString(R.string.notifications_basic_title),
            getString(R.string.notifications_short_content),
            NotificationCompat.PRIORITY_DEFAULT
        ).apply {
            actions.add(NotificationAction(android.R.drawable.ic_menu_send, getString(R.string.recipe_picker_facebook_login), fbLoginIntent))
            actions.add(NotificationAction(android.R.drawable.ic_dialog_email, getString(R.string.recipe_picker_google_login), gLoginIntent))
        })
    }
}