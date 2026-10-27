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
            "notif_101", "NOTIFICATIONS_FIRST_CHANNEL", NotificationManager.IMPORTANCE_LOW,
        ),
        NotificationChannel(
            "notif_102", "NOTIFICATIONS_SECOND_CHANNEL", NotificationManager.IMPORTANCE_DEFAULT,
        ),
        NotificationChannel(
            "notif_103", "NOTIFICATIONS_THIRD_CHANNEL", NotificationManager.IMPORTANCE_HIGH,
        )
    )
}