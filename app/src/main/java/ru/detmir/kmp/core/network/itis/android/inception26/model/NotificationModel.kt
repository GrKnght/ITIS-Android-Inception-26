package ru.detmir.kmp.core.network.itis.android.inception26.model

import android.app.Notification

sealed class NotificationModel(
    open val id: Int,
    open val title: String,
    open val contentText: String?,
)

class PromoNotification(
    override val id: Int,
    override val title: String,
    override val contentText: String?,
    val redirectLink: String,
) : NotificationModel(
    id = id,
    title = title,
    contentText = contentText,
)

class AuthNotification(
    override val id: Int,
    override val title: String,
    val authCode: String,
) : NotificationModel(
    id = id,
    title = title,
    contentText = null,
)

class DefaultNotification(
    override val id: Int,
    override val title: String,
    override val contentText: String?
) : NotificationModel(
    id = id,
    title = title,
    contentText = contentText,
)