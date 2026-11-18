package ru.detmir.kmp.core.network.itis.android.inception26.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.detmir.kmp.core.network.itis.android.inception26.model.CatModel

@Composable
fun DetailsRoute(model: CatModel) {
    DetailsContent(model)
}

@Composable
private fun DetailsContent(model: CatModel) {
    val sampleResult = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Download data from server
        launch(Dispatchers.IO) {
            // Get from server
        }
    }

    Scaffold { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            AsyncImage(model = model.url, contentDescription = null)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = model.name)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = model.desc)
        }
    }
}