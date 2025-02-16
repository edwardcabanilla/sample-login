package com.example.login.ui.generic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.SlateBlue

@Composable
fun LoadingBuilder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable {  },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                strokeWidth = 10.dp,
                modifier =
                Modifier
                    .size(70.dp),
                color = SlateBlue,
            )
            val dotCount = remember { mutableIntStateOf(0) }

            LaunchedEffect(Unit) {
                while (true) {
                    dotCount.intValue = (dotCount.intValue + 1) % 4
                    kotlinx.coroutines.delay(500L)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Loading",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 20.sp,
                        color = SlateBlue,
                        fontWeight = FontWeight.Normal
                    )
                )
                for (i in 1..3) {
                    AnimatedVisibility(visible = (i <= dotCount.intValue)) {
                        Text(
                            text = ".",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 20.sp,
                                color = SlateBlue,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }
        }
    }
}
