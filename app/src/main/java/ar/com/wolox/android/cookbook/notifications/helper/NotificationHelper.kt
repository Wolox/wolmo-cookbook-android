package ar.com.wolox.android.cookbook.notifications.helper

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.notifications.model.NotificationModel
import ar.com.wolox.android.cookbook.notifications.model.PictureExpandableNotification
import ar.com.wolox.android.cookbook.notifications.model.TextExpandableNotification

object NotificationHelper {

    private fun createNotification(context: Context, model: NotificationModel): Notification {
        return NotificationCompat
            .Builder(context, model.channelId
                ?: context.getString(R.string.notifications_general_channel_id))
            .apply {
                setSmallIcon(R.drawable.ic_notification)
                setContentTitle(model.title)
                setContentText(model.content)
                priority = model.priority

                when (model) {
                    is TextExpandableNotification -> {
                        setStyle(NotificationCompat.BigTextStyle().bigText(model.longText))
                    }
                    is PictureExpandableNotification -> {
                        setLargeIcon(model.picture)
                        setStyle(NotificationCompat.BigPictureStyle()
                            .bigPicture(model.picture)
                            .bigLargeIcon(null)
                            .setBigContentTitle(model.bigContentTitle))
                    }
                }

                model.actions.forEach {
                    val (iconId, title, actionIntent) = it
                    addAction(iconId, title, actionIntent)
                }
            }
            .build()
    }

    fun showNotification(context: Context, id: Int, notification: NotificationModel) {
        NotificationManagerCompat
            .from(context)
            .notify(id, createNotification(context, notification))
    }
}
