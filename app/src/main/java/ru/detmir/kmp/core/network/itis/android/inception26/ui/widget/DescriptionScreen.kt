package ru.detmir.kmp.core.network.itis.android.inception26.ui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlin.random.Random

private val links = listOf(
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS72JDMIudpZI4f6-B1eJp8d_4Wv2lgDSuUBhGxfFJ9Zg&s",
    "https://www.gstatic.com/marketing-cms/assets/images/99/87/54a544064389b8c695a56eb4d3dc/hero-s-4x.webp=s1500-fcrop64=1,00000000ffffffff-rw",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwLUCV-G76fA5XThA8D7e-bL1-Nm7mQyq63JsoGeJzN0j4i_cuU6u4Etg&s=10"
)

@Composable
fun DescriptionSample(index: Int) {
    val link = links[Random.nextInt(0, 3)]
    AsyncImage(
        model = link,
        contentDescription = null,
        modifier = Modifier.size(128.dp)
    )
}