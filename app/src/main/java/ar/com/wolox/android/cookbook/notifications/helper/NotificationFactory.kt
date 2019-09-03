package ar.com.wolox.android.cookbook.notifications.helper

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.notifications.model.InboxNotification
import ar.com.wolox.android.cookbook.notifications.model.SkeletalNotification
import ar.com.wolox.android.cookbook.notifications.model.PictureExpandableNotification
import ar.com.wolox.android.cookbook.notifications.model.TextExpandableNotification
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NotificationFactory @Inject constructor(@ApplicationScope val context: Context) {

    private fun createNotification(model: SkeletalNotification): Notification {
        return NotificationCompat.Builder(context, model.channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(model.title)
            .setContentText(model.content)
            .setPriority(model.priority)
            .setAutoCancel(true)
            .apply {
                when (model) {
                    is TextExpandableNotification -> {
                        setStyle(NotificationCompat.BigTextStyle().bigText(model.longText))
                    }
                    is PictureExpandableNotification -> {
                        val pictureBitmap =
                            BitmapFactory.decodeResource(context.resources, model.picture)
                        setLargeIcon(pictureBitmap)
                        setStyle(
                            NotificationCompat.BigPictureStyle()
                                .bigPicture(pictureBitmap)
                                .bigLargeIcon(null)
                                .setBigContentTitle(model.bigContentTitle)
                        )
                    }
                    is InboxNotification -> {
                        val inboxStyle = NotificationCompat.InboxStyle()

                        model.lines.forEach {
                            inboxStyle.addLine(it)
                        }

                        setStyle(inboxStyle)
                    }
                }

                model.actions.forEach { (iconId, title, actionIntent) ->
                    addAction(iconId, title, actionIntent)
                }
            }
            .build()
    }

    fun showNotification(id: Int, notification: SkeletalNotification) {
        NotificationManagerCompat
            .from(context)
            .notify(id, createNotification(notification))
    }
}
