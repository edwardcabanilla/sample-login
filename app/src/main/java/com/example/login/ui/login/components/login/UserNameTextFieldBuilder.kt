package com.example.login.ui.login.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserNameTextFieldBuilder(
    label: String,
    username: MutableState<String>,
    focusRequester: FocusRequester,
) {
    OutlinedTextField(
        value = username.value,
        onValueChange = {
            username.value = it
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .focusRequester(focusRequester)
    )
}