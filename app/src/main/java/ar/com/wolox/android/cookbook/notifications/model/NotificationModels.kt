package ar.com.wolox.android.cookbook.notifications.model

import android.app.PendingIntent
import android.graphics.Bitmap

sealed class NotificationModel(
    val channelId: String?,
    val title: String,
    val content: String,
    val priority: Int,
    val actions: MutableList<NotificationAction> = mutableListOf()
)

class BasicNotification(
    channelId: String?,
    title: String,
    content: String,
    priority: Int
) : NotificationModel(channelId, title, content, priority)

class TextExpandableNotification(
    channelId: String?,
    title: String,
    content: String,
    priority: Int,
    val longText: String
) : NotificationModel(channelId, title, content, priority)

class PictureExpandableNotification(
    channelId: String?,
    title: String,
    content: String,
    priority: Int,
    val bigContentTitle: String,
    val picture: Bitmap
) : NotificationModel(channelId, title, content, priority)

data class NotificationAction(val iconId: Int, val title: String, val actionIntent: PendingIntent)
