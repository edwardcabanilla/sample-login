package com.example.login.ui.login.components.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import com.example.login.ui.login.interfaces.LoginStateListener
import com.example.login.ui.theme.DimGray
import com.example.login.ui.theme.SlateBlue


@Composable
fun OnBoardingBuilder(listener: LoginStateListener) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            "Hello",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            "Lorem ipsum dolor sit amet, consectetur\nadipiscing elit.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 20.sp,
                color = DimGray,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                listener.goToLogin()
            },
            shape = RoundedCornerShape(30.dp),
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
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = White,
                contentColor = SlateBlue,
            ),
            border = BorderStroke(1.dp, SlateBlue),
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(300.dp)
                    .padding(vertical = 5.dp),
                text = "Sign Up",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Lorem ipsum dolor.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 14.sp,
                color = DimGray,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_instagram),
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp),
            )
        }
    }
}