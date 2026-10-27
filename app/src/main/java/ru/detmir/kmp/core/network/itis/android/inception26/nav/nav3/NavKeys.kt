package ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import ru.detmir.kmp.core.network.itis.android.inception26.model.CatModel

@Serializable
data object ListNav3Route : NavKey

@Serializable
data class DetailsNav3Route(
    val model: CatModel
) : NavKey

@Serializable
data object DialogSampleNav3Route : NavKey

@Serializable
data object BottomSheetDefaultNav3Route : NavKey