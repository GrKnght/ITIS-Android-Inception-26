package ru.detmir.kmp.core.network.itis.android.inception26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import ru.detmir.kmp.core.network.itis.android.inception26.ui.widget.DescriptionSample

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        println("TEST TAG - Sample")
        setContent {
            val currentRoute = remember { mutableStateOf<NavRoute>(NavRoute.Main) }

            BackHandler(enabled = currentRoute.value is NavRoute.Description) {
                currentRoute.value = NavRoute.Main
            }

            when (currentRoute.value) {
                is NavRoute.Main -> {
                    println("TEST TAG - Main branch")
                    LazyColumnSample(currentRoute)
                }

                is NavRoute.Description -> {
                    DescriptionSample((currentRoute.value as NavRoute.Description).index)
                }
            }
        }
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