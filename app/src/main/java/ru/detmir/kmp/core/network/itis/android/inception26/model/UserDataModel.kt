package ru.detmir.kmp.core.network.itis.android.inception26.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    val email: String,
    val password: String
) {
    companion object {
        val EMPTY = UserDataModel(email = "", password = "")
    }
}
