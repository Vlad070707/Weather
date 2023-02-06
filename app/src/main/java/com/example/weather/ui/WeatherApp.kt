package com.example.weather.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherApp() {
  WeatherTheme {
    val navController = rememberNavController()
    WeatherNavGraph(navController = navController)
  }
}