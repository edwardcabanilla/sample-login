package com.example.login.ui.login.components.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import com.example.login.framework.network.api.state.LoginState
import com.example.login.ui.generic.LoadingBuilder
import com.example.login.ui.login.interfaces.LoginStateListener
import com.example.login.ui.theme.DimGray
import com.example.login.ui.theme.SlateBlue
import com.example.login.viewmodels.LoginViewModel

@Composable
fun LoginBuilder(listener: LoginStateListener, loginViewModel: LoginViewModel) {
    val passwordTextFieldRequester = FocusRequester()
    val usernameTextFieldRequester = FocusRequester()
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }
    val loginState = loginViewModel.loginState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = "App Logo",
            modifier = Modifier.fillMaxWidth().padding( horizontal = 80.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            "Login",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        UserNameTextFieldBuilder(
            label = "Name",
            username = username,
            focusRequester = usernameTextFieldRequester,
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextFieldBuilder(
            label = "Password",
            password = password,
            focusRequester = passwordTextFieldRequester,
            isPasswordVisible = isPasswordVisible
        )
        AnimatedVisibility(
            visible = loginState is LoginState.Failure,
            enter =
            fadeIn(
                initialAlpha = 0.4f,
            ),
            exit =
            fadeOut(
                animationSpec = tween(durationMillis = 250),
            ),
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = when (loginState) {
                        is LoginState.Failure -> loginState.message
                        else -> ""
                    },
                    color = Red,
                )
            }
        }
        Text(
            "Lorem ipsum dolor.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 20.sp,
                color = DimGray,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                listener.login(username.value, password.value)
            },
            shape = RoundedCornerShape(40.dp),
            modifier = Modifier
                .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = SlateBlue,
                contentColor = White,
            ),
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(300.dp)
                    .padding(vertical = 5.dp),
                text = "Login",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }

    when (loginState) {
        LoginState.Loading -> LoadingBuilder()
        else -> {}
    }
}