package ru.detmir.kmp.core.network.itis.android.inception26.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    textFieldValue: String,
    onButtonClick: () -> Unit
) {
    Scaffold { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            Text(text = "Hello: $textFieldValue")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                onButtonClick.invoke()
            }) {
                Text(text = "Click me!")
            }
        }
    }
}