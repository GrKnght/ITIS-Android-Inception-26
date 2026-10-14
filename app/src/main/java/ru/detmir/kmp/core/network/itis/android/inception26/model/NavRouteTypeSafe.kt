package ru.detmir.kmp.core.network.itis.android.inception26.model

import kotlinx.serialization.Serializable

@Serializable
data class MainTypeSafeRoute(
    val userDataModel: UserDataModel
)

@Serializable
object ProfileTypeSafeRoute

@Serializable
object ListNavRoute

@Serializable
data class DetailsNavRoute(
    val catModel: CatModel
)