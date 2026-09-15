package ru.detmir.kmp.core.network.itis.android.inception26

sealed class GlobalError {

    class AuthorizationError(
        val firstField: String
    ) : GlobalError()

    class UserDataError(
        val secondField: String
    ) : GlobalError()
}

enum class GlobalErrorEnum {
    AuthorizationError,
    UserDataError
}