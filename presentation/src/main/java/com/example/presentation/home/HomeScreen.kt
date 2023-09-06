package com.example.presentation.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.base.Loader
import com.example.presentation.home.view.CurrentWeatherView
import com.example.presentation.home.view.ErrorView
import com.example.presentation.home.view.WeatherForNextDaysView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {

            when {
                uiState.value.isLoading -> {
                    Loader(
                        modifier = Modifier
                            .size(150.dp)
                    )
                }

                uiState.value.isError -> {
                    ErrorView {
                        viewModel.updateWeatherData()
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .verticalScroll(state = rememberScrollState())
                            .padding(bottom = 60.dp)
                    ) {
                        CurrentWeatherView(
                            viewState = uiState.value.currentWeather,
                            onShowMoreDetailsClicked = viewModel::onShowMoreDetailsClicked
                        )
                        AnimatedVisibility(
                            visible = uiState.value.currentWeather.showMoreDetails.not(),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            WeatherForNextDaysView(
                                viewState = uiState.value.futureWeather,
                                onTodayClicked = { viewModel.onDayOptionClicked(isTodayChecked = true) },
                                onTomorrowClicked = { viewModel.onDayOptionClicked(isTomorrowChecked = true) },
                                onNextFiveDaysClicked = { viewModel.onDayOptionClicked(isNextFiveDaysChecked = true) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(hiltViewModel())
}


