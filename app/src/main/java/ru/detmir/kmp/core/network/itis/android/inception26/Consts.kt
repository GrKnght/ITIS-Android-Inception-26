package ru.detmir.kmp.core.network.itis.android.inception26

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.dp

object Consts {

    val DEFAULT_PADDING_DP = 16.dp

    @RequiresApi(Build.VERSION_CODES.O)
    val notificationChannels = listOf(
        NotificationChannel(
            "notif_promo_101", "PROMOS_CHANNEL", NotificationManager.IMPORTANCE_DEFAULT,
        ),
        NotificationChannel(
            "notif_auth_102", "AUTH_MESSAGES_CHANNEL", NotificationManager.IMPORTANCE_HIGH,
        ),
        NotificationChannel(
            "notif_default_100", "DEFAULT_CHANNEL", NotificationManager.IMPORTANCE_DEFAULT,
        )
    )

    const val PROMO_NOTIFICATION_LINK_KEY = "promo_notification_link"
    const val FIRST_ACTION_EXTRA_KEY = "first_action_extra"
}