package ar.com.wolox.android.cookbook.notifications.helper

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.notifications.model.SkeletalNotification
import ar.com.wolox.android.cookbook.notifications.model.PictureExpandableNotification
import ar.com.wolox.android.cookbook.notifications.model.TextExpandableNotification
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NotificationFactory @Inject constructor(@ApplicationScope val context: Context) {

    private fun createNotification(model: SkeletalNotification): Notification {
        return NotificationCompat
            .Builder(context, model.channelId)
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
                        val pictureBitmap = BitmapFactory.decodeResource(context.resources, model.picture)
                        setLargeIcon(pictureBitmap)
                        setStyle(NotificationCompat.BigPictureStyle()
                            .bigPicture(pictureBitmap)
                            .bigLargeIcon(null)
                            .setBigContentTitle(model.bigContentTitle))
                    }
                }

                model.actions.forEach {
                    val (iconId, title, actionIntent) = it
                    addAction(iconId, title, actionIntent)
                }

                setAutoCancel(true)
            }
            .build()
    }

    fun showNotification(id: Int, notification: SkeletalNotification) {
        NotificationManagerCompat
            .from(context)
            .notify(id, createNotification(notification))
    }
}
