package ru.detmir.kmp.core.network.itis.android.inception26.model.navType

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.detmir.kmp.core.network.itis.android.inception26.model.UserDataModel

val userDataNavType = object : NavType<UserDataModel>(isNullableAllowed = false) {

    override fun get(
        bundle: SavedState,
        key: String
    ): UserDataModel? {
        val userDataString = bundle.getString(key) ?: return null
        return Json.decodeFromString<UserDataModel>(userDataString)
    }

    override fun put(
        bundle: SavedState,
        key: String,
        value: UserDataModel
    ) {
        val userDataString = Json.encodeToString(value)
        bundle.putString(key, userDataString)
    }

    override fun parseValue(value: String): UserDataModel {
        return Json.decodeFromString<UserDataModel>(value)
    }

    override fun serializeAsValue(value: UserDataModel): String {
        return Json.encodeToString(value)
    }
}