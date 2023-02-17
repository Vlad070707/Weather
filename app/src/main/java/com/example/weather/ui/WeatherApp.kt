package com.example.weather.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.ui.bottom_navigation.BottomNavigation
import com.example.weather.ui.bottom_navigation.Screen
import com.example.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherApp() {
  WeatherTheme {
    val navController = rememberNavController()
    val showNavigationBar = navController.currentBackStackEntryAsState().value?.destination?.route != Screen.Splash.route

    Surface {
      Scaffold(
        bottomBar = {
          if (showNavigationBar) {
            BottomNavigation(navController = navController)
          }
        }
      ) {
        WeatherNavGraph(navController = navController)
      }
    }
  }
}