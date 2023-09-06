package com.example.presentation.splash_screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.splash_screen.view.LoadingView
import kotlinx.coroutines.delay

private const val SPLASH_LOADING_TIME = 2000L

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
            NavigateNext(navigateHome)
        } else {
            NavigateNext(navigateLocation)
        }
    }
}

@Composable
fun NavigateNext(navigateNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(SPLASH_LOADING_TIME)
        navigateNext()
    }
}