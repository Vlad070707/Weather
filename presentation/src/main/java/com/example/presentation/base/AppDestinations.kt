package com.example.presentation.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppDestinations(
    val route: String,
    val title: String,
    var isAvailable: Boolean = true,
    val icon: ImageVector? = null
) {

    object Home : AppDestinations(route = "home", title = "Home", icon = Icons.Outlined.Home)
    object Location : AppDestinations(route = "location", title = "Location", icon = Icons.Outlined.LocationOn)
    object Splash : AppDestinations(route = "splash", title = "Splash")
}