package ru.detmir.kmp.core.network.itis.android.inception26

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.lifecycleScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.BottomSheetDefaultNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.DetailsNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.DialogSampleNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.ListNav3Route
import ru.detmir.kmp.core.network.itis.android.inception26.nav.nav3.strategy.BottomSheetSceneStrategy
import ru.detmir.kmp.core.network.itis.android.inception26.screen.bottomsheet.DefaultBottomSheet
import ru.detmir.kmp.core.network.itis.android.inception26.screen.details.DetailsRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.dialogFirst.DialogContent
import ru.detmir.kmp.core.network.itis.android.inception26.screen.list.ListRoute
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

class AuthException(message: String, cause: Throwable?) : Throwable(message, cause)

class MainActivity : ComponentActivity() {

    private val customScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Coroutine context = Job() + Name + Error Handler + Dispatcher
        val handler = CoroutineExceptionHandler { ctx, ex ->
            if (ex is AuthException) {
                // Logout
                println("TEST TAG - 401-st code received, logout")
            } else {
                // Default
            }
        }

        lifecycleScope.async {

        }

        customScope.launch(handler) {

            val showOnUi = launch(Dispatchers.Main) {
                delay(2000L)
                Toast.makeText(this@MainActivity, "Hello Toast", Toast.LENGTH_SHORT).show()
            }

            val third = launch {
                println("TEST TAG - Third")
                delay(3000L)
                throw AuthException(message = "API returned 401", cause = null)
            }

            val asyncResult = async {
                //
                delay(5000L)
                "Server response"
            }.await()
        }

        customScope.launch(Dispatchers.Main) {
            delay(5000L)
            Toast.makeText(this@MainActivity, "Toast after 5 seconds", Toast.LENGTH_SHORT).show()
        }

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
                            backstack.add(DetailsNav3Route(model))
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
}