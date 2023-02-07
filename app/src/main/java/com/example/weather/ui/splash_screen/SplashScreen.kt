package com.example.weather.ui.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weather.R
import com.example.weather.ui.WeatherDestination
import com.example.weather.ui.WeatherNavGraph

@Composable
fun SplashScreen(navController: NavHostController) {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.drawable.d01))
    val logoAnimationState =
      animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
      composition = composition,
      progress = { logoAnimationState.progress }
    )
    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
      navController.navigate(WeatherDestination.HOME_ROUTE)
    }
  }
}