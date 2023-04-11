package com.example.presentation.splash_screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.util.RequestState
import com.example.presentation.base.Screen
import com.example.presentation.splash_screen.sections.LoadingSection
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val savedCity = viewModel.currentCityState.collectAsState()

    LoadingSection()
    
    LaunchedEffect(savedCity) {
        if (savedCity.value !is RequestState.Loading) {
            delay(3000)
            val navigationRoute = if (savedCity.value.data?.isNotEmpty() == true) {
                Screen.Home.route
            } else {
                Screen.Location.route
            }
            navController.navigate(navigationRoute) {
                popUpTo(0)
            }
        }
    }
}