package ru.detmir.kmp.core.network.itis.android.inception26.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import ru.detmir.kmp.core.network.itis.android.inception26.Consts
import ru.detmir.kmp.core.network.itis.android.inception26.R
import ru.detmir.kmp.core.network.itis.android.inception26.model.NotificationModel

class NotificationHandler(
    private val ctx: Context,
) {
    private val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun initNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannels(Consts.notificationChannels)
        }
    }

    fun sendMessage(model: NotificationModel) {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Consts.notificationChannels[2].id
        } else {
            DEFAULT_NOTIFICATION_CHANNEL
        }
        val builder = NotificationCompat.Builder(ctx, channel)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(model.title)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        model.contentText?.let(builder::setContentText)

        notificationManager.notify(model.id, builder.build())

    }

    private companion object {
        const val DEFAULT_NOTIFICATION_CHANNEL = "INCEPTION_26_DEFAULT"

    }
}