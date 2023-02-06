package com.example.weather.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object WeatherDestination {
  const val HOME_ROUTE = "home"
  const val DETAILS_ROUTE = "details"
}

class WeatherNavigationActions(navController: NavHostController) {

  val navigateToHome: () -> Unit = {
    navController.navigate(WeatherDestination.HOME_ROUTE) {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
  }

  val navigateToDetails: () -> Unit = {
    navController.navigate(WeatherDestination.DETAILS_ROUTE) {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
  }
}