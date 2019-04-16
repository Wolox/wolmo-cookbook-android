package ar.com.wolox.android.cookbook.notifications.helper

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import ar.com.wolox.android.cookbook.R

object NotificationChannelHelper {

    private val notificationChannels: MutableList<NotificationChannel> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(channelModel: NotificationChannelModel): NotificationChannel =
        NotificationChannel(channelModel.id, channelModel.name, channelModel.priority)
            .apply {
                this.description = channelModel.description
            }

    fun init(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createGeneralChannel(context)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createGeneralChannel(context: Context) {
        val channelName = context.getString(R.string.notifications_general_channel)
        val channelDescription = context.getString(R.string.notifications_general_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val generalNotification = NotificationChannelModel(context.getString(R.string.notifications_general_channel_id), channelName, channelDescription, importance)
        val channel = createChannel(generalNotification)

        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        notificationChannels.add(channel)
    }

    data class NotificationChannelModel(
        val id: String,
        val name: String,
        val description: String,
        val priority: Int
    )
}