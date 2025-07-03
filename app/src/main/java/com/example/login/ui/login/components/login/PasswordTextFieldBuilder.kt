package com.example.login.ui.login.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import com.example.login.ui.theme.DimGray

@Composable
fun PasswordTextFieldBuilder(
    label: String,
    password: MutableState<String>,
    isPasswordVisible: MutableState<Boolean>,
    focusRequester: FocusRequester,
) {
    OutlinedTextField(
        value = password.value,
        onValueChange = {
            password.value = it
        },
        label = {
            Text(
                text = label,
                style =
                MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 16.sp,
                ),
            )
        },
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = painterResource(id = if (isPasswordVisible.value) R.drawable.ic_visible else R.drawable.ic_visible_slash)
            IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                Icon(
                    painter = image,
                    contentDescription = "App Logo",
                    tint = DimGray,
                    modifier = Modifier.size(20.dp),
                )
            }
        },
        modifier =
        Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .padding(horizontal = 50.dp)
    )
}