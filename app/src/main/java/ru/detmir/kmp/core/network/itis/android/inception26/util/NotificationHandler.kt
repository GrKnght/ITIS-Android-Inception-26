package ru.detmir.kmp.core.network.itis.android.inception26.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import ru.detmir.kmp.core.network.itis.android.inception26.Consts
import ru.detmir.kmp.core.network.itis.android.inception26.MainActivity
import ru.detmir.kmp.core.network.itis.android.inception26.R
import ru.detmir.kmp.core.network.itis.android.inception26.model.AuthNotification
import ru.detmir.kmp.core.network.itis.android.inception26.model.NotificationModel
import ru.detmir.kmp.core.network.itis.android.inception26.model.NotificationType
import ru.detmir.kmp.core.network.itis.android.inception26.model.PromoNotification

class NotificationHandler(
    private val ctx: Context,
) {
    private val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private var counter = 0

    fun initNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannels(Consts.notificationChannels)
        }
    }

    fun sendMessage(model: NotificationModel) {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (model) {
                is PromoNotification -> {
                    Consts.notificationChannels.first().id
                }

                is AuthNotification -> {
                    Consts.notificationChannels[1].id
                }

                else -> {
                    Consts.notificationChannels.last().id
                }
            }
        } else {
            DEFAULT_NOTIFICATION_CHANNEL
        }
        val titleText = if (counter++ % 2 == 0) {
            model.title
        } else {
            "Modified title text: $counter"
        }

        val firstActionIntent = Intent(ctx, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(Consts.FIRST_ACTION_EXTRA_KEY, "Some text to test")
        }
        val firstActionPI = PendingIntent.getActivity(
            ctx, PENDING_INTENT_REQUEST_CODE, firstActionIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(ctx, channel)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(titleText)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .addAction(
                R.mipmap.ic_launcher, "Sequence", firstActionPI
            )
            .addAction(
                R.mipmap.ic_launcher, "Sequence 2", firstActionPI
            )
            .addAction(
                R.mipmap.ic_launcher, "Sequence 3 ", firstActionPI
            )
            .addAction(
                R.mipmap.ic_launcher, "Sequence 4", firstActionPI
            )
            .addAction(
                R.mipmap.ic_launcher, "Sequence 5", firstActionPI
            )


        if (model is PromoNotification) {
            val intent = Intent(ctx, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(Consts.PROMO_NOTIFICATION_LINK_KEY, model.redirectLink)
            }
            val pendingIntent = PendingIntent.getActivity(
                ctx,
                PENDING_INTENT_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            builder.setContentIntent(
                pendingIntent
            )
        }

        model.contentText?.let(builder::setContentText)

        if (counter % 2 == 0) {
            notificationManager.notify(model.id, builder.build())
        } else {
            notificationManager.cancel(model.id)
        }
    }

    private companion object {
        const val DEFAULT_NOTIFICATION_CHANNEL = "INCEPTION_26_DEFAULT"
        const val PENDING_INTENT_REQUEST_CODE = 125213
    }
}