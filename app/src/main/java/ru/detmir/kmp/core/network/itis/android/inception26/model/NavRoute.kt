package ru.detmir.kmp.core.network.itis.android.inception26.model

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    data object Main : NavRoute()

    data object Auth : NavRoute()

    data class Profile(
        val email: String,
        val password: String,
    ) : NavRoute()

    data class Description(val index: Int) : NavRoute()
}