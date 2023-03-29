package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.base.Screen
import com.example.presentation.home.HomeScreen
import com.example.presentation.location.LocationScreen
import com.example.presentation.splash_screen.SplashScreen

@Composable
fun WeatherNavGraph(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Splash.route,
  ) {
    composable(Screen.Home.route) {
      HomeScreen()
    }
    composable(Screen.Location.route) {
      LocationScreen()
    }
    composable(Screen.Splash.route) {
      SplashScreen(navController = navController)
    }
  }
}