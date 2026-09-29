package ru.detmir.kmp.core.network.itis.android.inception26.screen.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.detmir.kmp.core.network.itis.android.inception26.Consts
import ru.detmir.kmp.core.network.itis.android.inception26.R
import ru.detmir.kmp.core.network.itis.android.inception26.model.UserDataModel

@Composable
fun AuthRoute(
    onAuthButtonClicked: (UserDataModel) -> Unit
) {
    val emailTextState = rememberSaveable { mutableStateOf("") }
    val emailError = remember { mutableStateOf(false) }
    val passwordTextState = rememberSaveable { mutableStateOf("") }
    val passwordError = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 64.dp)
                    .verticalScroll(scrollState)
            ) {
                AsyncImage(
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGvODIDVnNXl10E_xFMlx6QQHs54YXGnq7vMB8MlRlRw&s=10",
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 64.dp, top = Consts.DEFAULT_PADDING_DP, end = 64.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = Consts.DEFAULT_PADDING_DP,
                            top = Consts.DEFAULT_PADDING_DP,
                            end = Consts.DEFAULT_PADDING_DP,
                        ),
                    value = emailTextState.value,
                    onValueChange = { input ->
                        emailError.value = false
                        emailTextState.value = input
                    },
                    label = {
                        Text(text = stringResource(R.string.email_text_field_label))
                    },
                    supportingText = {
                        if (emailError.value) {
                            Text(text = stringResource(R.string.email_not_matches_error))
                        }
                    },
                    isError = emailError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    maxLines = 1,
                )

                Spacer(modifier = Modifier.height(Consts.DEFAULT_PADDING_DP))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = Consts.DEFAULT_PADDING_DP,
                            end = Consts.DEFAULT_PADDING_DP,
                        ),
                    value = passwordTextState.value,
                    onValueChange = { input ->
                        passwordError.value = false
                        passwordTextState.value = input
                    },
                    label = {
                        Text(text = stringResource(R.string.password_text_field_label))
                    },
                    supportingText = {
                        if (passwordError.value) {
                            Text(text = stringResource(R.string.password_length_error))
                        }
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = passwordError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    maxLines = 1,
                )
            }
            val ctx = LocalContext.current

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Consts.DEFAULT_PADDING_DP)
                    .padding(top = 24.dp)
                    .align(Alignment.BottomCenter),
                onClick = {
                    val emailPattern = Patterns.EMAIL_ADDRESS
                    val emailCheck = emailPattern.matcher(emailTextState.value)
                    if (!emailCheck.matches()) {
                        emailError.value = true
                        return@Button
                    }

                    if (passwordTextState.value.length < 8) {
                        passwordError.value = true
                        return@Button
                    }

                    onAuthButtonClicked.invoke(
                        UserDataModel(
                            email = emailTextState.value,
                            password = passwordTextState.value
                        )
                    )
                }
            ) {
                Text(text = stringResource(R.string.auth_button_text))
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface {
        AuthRoute(onAuthButtonClicked = {})
    }
}