package com.example.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.base.Screen
import com.example.presentation.bottom_navigation.BottomNavigation
import com.example.presentation.theme.WeatherTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherApp() {
    WeatherTheme {
        val navController = rememberNavController()
        val showNavigationBar = navController.currentBackStackEntryAsState().value?.destination?.route?.let { route ->
            route != (Screen.Splash.route)
        } ?: false

        Surface {
            Scaffold(
                bottomBar = {
                    if (showNavigationBar) {
                        BottomNavigation(navController = navController)
                    }
                },
                content = {
                    WeatherNavGraph(navController = navController)
                }
            )
        }
    }
}