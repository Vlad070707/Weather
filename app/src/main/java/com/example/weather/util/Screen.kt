package com.example.weather.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
  val route: String,
  val title: String,
  var isAvailable: Boolean = true,
  val icon: ImageVector? = null
) {
  class Home : Screen(route = "home", title = "Home", icon = Icons.Outlined.Home)
  class Location : Screen(route = "location", title = "Location", icon = Icons.Outlined.LocationOn)
  class Splash : Screen(route = "splash", title = "Splash")
}