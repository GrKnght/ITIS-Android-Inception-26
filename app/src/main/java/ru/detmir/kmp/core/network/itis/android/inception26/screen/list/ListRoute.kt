package ru.detmir.kmp.core.network.itis.android.inception26.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.detmir.kmp.core.network.itis.android.inception26.model.CatModel
import ru.detmir.kmp.core.network.itis.android.inception26.repo.DataRepository

@Composable
fun ListRoute(onItemClick: (CatModel) -> Unit) {
    ListContent(onItemClick)
}

@Composable
private fun ListContent(
    onItemClick: (CatModel) -> Unit,
) {
    LazyColumn(modifier = Modifier.systemBarsPadding()) {
        items(
            count = DataRepository.catsData.size,
            key = { position ->
                DataRepository.catsData[position].name
            }
        ) { position ->
            CatItem(model = DataRepository.catsData[position], onItemClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CatItem(
    model: CatModel,
    onItemClick: (CatModel) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(model)
            }
            .padding(horizontal = 16.dp),
        text = model.name
    )
}