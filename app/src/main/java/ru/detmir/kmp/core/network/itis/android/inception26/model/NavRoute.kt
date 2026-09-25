package ru.detmir.kmp.core.network.itis.android.inception26.model

sealed class NavRoute {

    data object Main : NavRoute()

    data class Description(val index: Int) : NavRoute()
}