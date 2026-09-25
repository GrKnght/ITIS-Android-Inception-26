package ru.detmir.kmp.core.network.itis.android.inception26.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextExample(
    arg1: String,
    arg2: String?,
) {
     Surface(
        color = Color.Green,
        modifier = Modifier
            .width(320.dp)


    ) {
        Row {
            Text(text = arg1)
            arg2?.let { Text(text = it) }
        }
    }
}

@Preview
@Composable
private fun FirstPreview() {
    CustomTextExample(
        arg1 = "First arg", arg2 = null
    )
}

@Preview
@Composable
private fun SecondPreview() {
    CustomTextExample(
        arg1 = "First arg", arg2 = "AnotherArg"
    )
}