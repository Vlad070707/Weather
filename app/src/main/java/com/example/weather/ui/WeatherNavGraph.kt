package com.example.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.R
import com.example.weather.ui.home.HomeScreen

@Composable
fun WeatherNavGraph(
  navController: NavHostController = rememberNavController()
) {
  NavHost(
    navController = navController,
    startDestination = WeatherDestination.HOME_ROUTE,
  ) {
    composable(WeatherDestination.HOME_ROUTE) {
      HomeScreen()
    }
    composable(WeatherDestination.DETAILS_ROUTE) {
      CircularProgressIndicator(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xFF11103A))
          .wrapContentSize(align = Alignment.Center),
        color = colorResource(id = R.color.dark_yellow)
      )
    }
  }
}