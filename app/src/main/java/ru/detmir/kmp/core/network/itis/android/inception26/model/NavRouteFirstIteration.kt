package ru.detmir.kmp.core.network.itis.android.inception26.model

enum class LegacyNavRoutes(
    val route: String
) {
    Profile(route = "NavProfile"),
    Main(route = "NavMain")
}

object NavKeys {

    object Main {
        const val TEXT_FIELD_ROUTE_KEY = "nav_main_text_field_route"
        const val TEXT_FIELD_ARG_KEY = "first_arg"
    }
}