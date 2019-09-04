package ar.com.wolox.android.cookbook.notifications.model

import android.app.PendingIntent
import androidx.annotation.DrawableRes

sealed class SkeletalNotification(
    val channelId: String,
    val title: String,
    val content: String,
    val priority: Int,
    val actions: MutableList<NotificationAction> = mutableListOf()
)

class BasicNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int
) : SkeletalNotification(channelId, title, content, priority)

class TextExpandableNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    val longText: String
) : SkeletalNotification(channelId, title, content, priority)

class PictureExpandableNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    val bigContentTitle: String,
    @DrawableRes val picture: Int
) : SkeletalNotification(channelId, title, content, priority)

class InboxNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    val lines: List<String>
) : SkeletalNotification(channelId, title, content, priority)

data class NotificationAction(
    val iconId: Int,
    val title: String,
    val actionIntent: PendingIntent
)

enum class NotificationType(val code: Int) {
    BASIC(1),
    TEXT_EXPANDABLE(2),
    PICTURE_EXPANDABLE(3),
    BASIC_ACTION(4),
    INBOX(5)
}
