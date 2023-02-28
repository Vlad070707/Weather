package com.example.weather.ui.splash_screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weather.util.Screen
import com.example.weather.ui.splash_screen.sections.LoadingSection
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  navController: NavHostController,
  viewModel: SplashScreenViewModel = hiltViewModel()
) {
  val savedCity = viewModel.currentCityState.collectAsState()

  LoadingSection()
  LaunchedEffect(true) {
    delay(3000)
    if (savedCity.value.isNotEmpty()) {
      navController.navigate(Screen.Home.route) {
        popUpTo(0)
      }
    } else {
      navController.navigate(Screen.Location.route) {
        popUpTo(0)
      }
    }
  }
}