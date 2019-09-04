package ar.com.wolox.android.cookbook.notifications.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
@RequiresApi(Build.VERSION_CODES.O)
class NotificationChannelFactory @Inject constructor(@ApplicationScope val context: Context) {

    private val notificationChannels: MutableList<NotificationChannel> = arrayListOf()

    fun createChannel(channelInfo: NotificationChannelInfo): NotificationChannel =
        NotificationChannel(channelInfo.id, channelInfo.name, channelInfo.priority).apply {
                this.description = channelInfo.description
            }

    fun init() {
        val generalChannel = createChannel(
            NotificationChannelInfo(
                context.getString(R.string.notifications_general_channel_id),
                context.getString(R.string.notifications_general_channel),
                context.getString(R.string.notifications_general_channel_description),
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(generalChannel)
        notificationChannels.add(generalChannel)
    }

    data class NotificationChannelInfo(
        val id: String,
        val name: String,
        val description: String,
        val priority: Int
    )
}