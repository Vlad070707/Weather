package com.example.presentation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.presentation.base.AppDestinations
import com.example.presentation.home.HomeScreen
import com.example.presentation.location.LocationScreen
import com.example.presentation.splash_screen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun WeatherNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestinations.Splash.route,
        exitTransition = {
            fadeOut()
        },
        enterTransition = {
            fadeIn()
        }
    ) {
        composable(
            route = AppDestinations.Home.route,
        ) {
            HomeScreen()
        }
        composable(
            route = AppDestinations.Location.route
        ) {
            LocationScreen()
        }
        composable(
            route = AppDestinations.Splash.route
        ) {
            SplashScreen(
                navigateHome = {
                    navController.navigate(AppDestinations.Home.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                },
                navigateLocation = {
                    navController.navigate(AppDestinations.Location.route) {
                        launchSingleTop = true
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}