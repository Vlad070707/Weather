package com.example.weather.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
  val route: String,
  val title: String,
  val icon: ImageVector? = null
) {
  object Home : Screen("home", "Home", Icons.Outlined.Home)
  object Location : Screen("location", "Location", Icons.Outlined.LocationOn)
  object Splash : Screen("splash", "Splash")

  object Items {
    val list = listOf(Home, Location)
  }
}
