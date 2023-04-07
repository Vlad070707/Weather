package com.example.presentation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.presentation.base.Screen
import com.example.presentation.home.HomeScreen
import com.example.presentation.location.LocationScreen
import com.example.presentation.splash_screen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun WeatherNavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        exitTransition = {
            fadeOut()
        },
        enterTransition = {
            fadeIn()
        }
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen()
        }
        composable(
            route = Screen.Location.route
        ) {
            LocationScreen()
        }
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
    }
}