package com.example.weather.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weather.ui.bottom_navigation.Screen
import com.example.weather.ui.home.HomeScreen
import com.example.weather.ui.splash_screen.SplashScreen

@Composable
fun WeatherNavGraph(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home.id,
  ) {
    composable(Screen.Home.id) {
      HomeScreen()
    }
    composable(Screen.Location.id) {
      LocationScreen()
    }
    composable(Screen.Splash.id){
      SplashScreen(navController = navController)
    }
  }
}