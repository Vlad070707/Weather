package com.example.weather.ui.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weather.R
import com.example.weather.ui.bottom_navigation.Screen
import com.example.weather.ui.home.HomeViewModel
import com.example.weather.util.Resource

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SplashScreen(
  navController: NavHostController,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val currentWeatherState = viewModel.currentWeatherState.collectAsState()
  val futureWeatherState = viewModel.futureWeatherState.collectAsState()
  val isLoading = currentWeatherState.value is Resource.Loading || futureWeatherState.value is Resource.Loading
  if (isLoading) {
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
    }
  } else {
    navController.navigate(Screen.Home.id)
  }

}