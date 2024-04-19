package com.example.presentation.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.presentation.R

@Composable
fun SplashScreen(
    navigateToCatalogScreen: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.splash_screen_animation)
        )

        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = { logoAnimationState.progress }
        )

        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navigateToCatalogScreen()
        }
    }
}