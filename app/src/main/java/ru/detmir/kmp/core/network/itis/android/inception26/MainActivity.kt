package ru.detmir.kmp.core.network.itis.android.inception26

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import ru.detmir.kmp.core.network.itis.android.inception26.model.AuthNotification
import ru.detmir.kmp.core.network.itis.android.inception26.model.PromoNotification
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.BottomSheetDefaultNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.DetailsNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.DialogSampleNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.ListNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.strategy.BottomSheetSceneStrategy
import ru.detmir.kmp.core.network.itis.android.inception26.screen.bottomsheet.DefaultBottomSheet
import ru.detmir.kmp.core.network.itis.android.inception26.screen.details.DetailsRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.dialogFirst.DialogContent
import ru.detmir.kmp.core.network.itis.android.inception26.screen.list.ListRoute
import ru.detmir.kmp.core.network.itis.android.inception26.util.NotificationHandler

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.getString(Consts.PROMO_NOTIFICATION_LINK_KEY)?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        enableEdgeToEdge()
        val notifHandler = NotificationHandler(ctx = applicationContext)
        notifHandler.initNotifications()

        setContent {
            val backstack = rememberNavBackStack(elements = arrayOf(ListNav3Route))
            val dialogStrategy = remember { DialogSceneStrategy<NavKey>() }
            val bottomSheetStrategy = remember { BottomSheetSceneStrategy<NavKey>() }

            NavDisplay(
                backStack = backstack,
                onBack = { backstack.removeLastOrNull() },
                sceneStrategies = listOf(
                    bottomSheetStrategy,
                    dialogStrategy,
                ),
                entryProvider = entryProvider {
                    entry<ListNav3Route> {
                        ListRoute { model ->
                            notifHandler.sendMessage(
//                                model = PromoNotification(
//                                    id = 1,
//                                    title = "Hello from Android",
//                                    contentText = "Some content text sample",
//                                    redirectLink = "https://somelink.sample.com/"
//                                )
                                model = AuthNotification(
                                    id = 1,
                                    title = "Hello from Android",
                                    authCode = "101412",
                                )
                            )
                        }
                    }

                    entry<DetailsNav3Route> { entry ->
                        DetailsRoute(entry.model)
                    }

                    entry<BottomSheetDefaultNav3Route>(
                        metadata = BottomSheetSceneStrategy.bottomSheet()
                    ) {
                        DefaultBottomSheet()
                    }

                    entry<DialogSampleNav3Route>(
                        metadata = DialogSceneStrategy.dialog(
                            DialogProperties(
                                dismissOnBackPress = false,
                                windowTitle = "Default dialog"
                            )
                        )
                    ) {
                        DialogContent()
                    }
                }
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.extras?.getString(Consts.PROMO_NOTIFICATION_LINK_KEY)?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        intent.extras?.getString(Consts.FIRST_ACTION_EXTRA_KEY)?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}