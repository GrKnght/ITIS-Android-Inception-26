package ru.detmir.kmp.core.network.itis.android.inception26.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileRoute(
    email: String,
    password: String
) {
    val topBarTextState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = topBarTextState.value,
                onValueChange = { input ->
                    topBarTextState.value = input
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Text(text = "Click me!")
            }
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            Text(text = email)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = password)
        }
    }
}