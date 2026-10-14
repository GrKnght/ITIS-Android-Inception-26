package ru.detmir.kmp.core.network.itis.android.inception26.model

import kotlinx.serialization.Serializable

@Serializable
data class CatModel(
    val name: String,
    val url: String,
    val desc: String
)
