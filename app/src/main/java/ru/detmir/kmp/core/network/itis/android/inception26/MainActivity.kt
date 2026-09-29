package ru.detmir.kmp.core.network.itis.android.inception26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import ru.detmir.kmp.core.network.itis.android.inception26.model.NavRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.auth.AuthRoute
import ru.detmir.kmp.core.network.itis.android.inception26.screen.profile.ProfileRoute
import ru.detmir.kmp.core.network.itis.android.inception26.ui.widget.DescriptionSample

class MainActivity : ComponentActivity() {

    private var backStack = mutableSetOf<NavRoute>(NavRoute.Auth)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val currentRoute = remember { mutableStateOf(backStack.last()) }

            BackHandler(enabled = backStack.size > 1) {
                val last = backStack.last()
                backStack.remove(last)
                val newLast = backStack.last()
                currentRoute.value = newLast
            }

            when (currentRoute.value) {
                is NavRoute.Main -> {
                    LazyColumnSample(currentRoute)
                }

                is NavRoute.Auth -> {
                    AuthRoute { userData ->
                        currentRoute.value = NavRoute.Profile(
                            email = userData.email,
                            password = userData.password
                        )
                        backStack.add(currentRoute.value)
                    }
                }

                is NavRoute.Profile -> {
                    val email = (currentRoute.value as NavRoute.Profile).email
                    val pass = (currentRoute.value as NavRoute.Profile).password
                    ProfileRoute(email = email, password = pass)
                }

                is NavRoute.Description -> {
                    DescriptionSample((currentRoute.value as NavRoute.Description).index)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val result = backStack.toTypedArray()
        outState.putSerializable(BACKSTACK_TAG, result)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getSerializable(BACKSTACK_TAG)?.let { result ->
            (result as? Array<NavRoute>)?.let {
                backStack = it.toMutableSet()
            }
        }
    }

    private companion object {
        const val BACKSTACK_TAG = "NAV_BACKSTACK"
    }
}

@Composable
private fun DefaultColumn() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        repeat(30) { index ->
            val backgroundColor = when {
                index % 5 == 0 -> Color.Green

                index % 3 == 0 -> Color.Red

                index % 2 == 0 -> Color.Blue

                else -> Color.Black
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(backgroundColor)
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable {
                        println("TEST TAG - Current Index: $index")

                    }
            ) {
                val textColor = if (backgroundColor == Color.Black || backgroundColor == Color.Blue) {
                    Color.White
                } else {
                    Color.Black
                }

                Text(
                    text = index.toString(),
                    color = textColor,
                    fontSize = TextUnit(32f, TextUnitType.Sp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LazyColumnSample(
    currentRoute: MutableState<NavRoute>
) {
    LazyColumn {
        item {
            Text(text = "Some Header Text to show", fontSize = TextUnit(32f, TextUnitType.Sp))
        }

        items(count = 30, key = { "data:$it" }) { index ->
            val backgroundColor = when {
                index % 5 == 0 -> Color.Green

                index % 3 == 0 -> Color.Red

                index % 2 == 0 -> Color.Blue

                else -> Color.Black
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(backgroundColor)
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable {
                        println("TEST TAG - Current Index: $index")
                        currentRoute.value = NavRoute.Description(index)
                    }
            ) {
                val textColor = if (backgroundColor == Color.Black || backgroundColor == Color.Blue) {
                    Color.White
                } else {
                    Color.Black
                }

                Text(
                    text = index.toString(),
                    color = textColor,
                    fontSize = TextUnit(32f, TextUnitType.Sp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = name)
        AnotherTextToShow()
    }
}

@Preview
@Composable
private fun GreetingPreview() {
    Greeting(name = "Preview text")
}

@Composable
fun AnotherTextToShow() {
    Text(text = "Another Text")
}