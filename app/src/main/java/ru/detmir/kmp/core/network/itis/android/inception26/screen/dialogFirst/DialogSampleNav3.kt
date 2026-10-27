package ru.detmir.kmp.core.network.itis.android.inception26.screen.dialogFirst

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun DialogContent() {
    Surface(modifier = Modifier.size(200.dp)) {
        Column {

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.inverseSurface
                )

            ) {
                Text(text = "Click!")
            }
        }
    }
}