package ru.detmir.kmp.core.network.itis.android.inception26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.detmir.kmp.core.network.itis.android.inception26.model.CatModel
import ru.detmir.kmp.core.network.itis.android.inception26.model.DetailsNavRoute
import ru.detmir.kmp.core.network.itis.android.inception26.model.ListNavRoute
import ru.detmir.kmp.core.network.itis.android.inception26.model.MainTypeSafeRoute
import ru.detmir.kmp.core.network.itis.android.inception26.model.ProfileTypeSafeRoute
import ru.detmir.kmp.core.network.itis.android.inception26.model.UserDataModel
import ru.detmir.kmp.core.network.itis.android.inception26.model.navType.catModelNavType
import ru.detmir.kmp.core.network.itis.android.inception26.model.navType.userDataNavType
import ru.detmir.kmp.core.network.itis.android.inception26.screen.details.DetailsRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.list.ListRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.main.MainScreen
import ru.detmir.kmp.core.network.itis.android.inception26.screen.profile.ProfileRoute
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ListNavRoute,
            ) {
                composable<ListNavRoute> { entry ->
                    ListRoute { model ->
                        navController.navigate(DetailsNavRoute(model))
                    }
                }

                composable<DetailsNavRoute>(
                    typeMap = mapOf(typeOf<CatModel>() to catModelNavType)
                ) { entry ->
                    val data = entry.toRoute<DetailsNavRoute>().catModel
                    DetailsRoute(data)
                }

                composable<ProfileTypeSafeRoute> { entry ->
                    ProfileRoute(
                        email = "E-mail sample",
                        password = "Password sample",
                    ) { userData ->
                        navController.navigate(
                            MainTypeSafeRoute(userDataModel = userData)
                        )
                    }
                }

                composable<MainTypeSafeRoute>(
                    typeMap = mapOf(typeOf<UserDataModel>() to userDataNavType),
                ) { entry ->
                    val args = entry.toRoute<MainTypeSafeRoute>()
                    MainScreen(textFieldValue = args.userDataModel.email) {
                        println("TEST TAG - Clicked!")
                    }
                }
            }
        }
    }
}