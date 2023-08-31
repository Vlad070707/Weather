package com.example.presentation.splash_screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.splash_screen.view.LoadingView

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navigateHome: () -> Unit,
    navigateLocation: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LoadingView()

    if (uiState.value.isLoading.not()) {
        if (uiState.value.currentCity.isNotEmpty()){
            navigateHome()
        } else {
            navigateLocation()
        }
    }
}