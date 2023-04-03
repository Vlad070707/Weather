package com.example.presentation.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.util.Resource
import com.example.presentation.base.Loader
import com.example.presentation.home.sections.CurrentWeatherSection
import com.example.presentation.home.sections.ErrorSection
import com.example.presentation.home.sections.WeatherForNextDaysSection

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val currentWeatherState = viewModel.currentWeatherState.collectAsState()
    val futureWeatherState = viewModel.futureWeatherState.collectAsState()

    val isLoading = currentWeatherState.value is Resource.Loading || futureWeatherState.value is Resource.Loading
    val isSuccess = currentWeatherState.value is Resource.Success && futureWeatherState.value is Resource.Success

    var isShowMoreDetailsClicked by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when (isLoading) {
            true -> {
                Loader(
                    modifier = Modifier
                        .size(150.dp)
                )
            }
            false -> {
                if (isSuccess) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        CurrentWeatherSection(
                            isShowMoreDetailsClicked,
                            currentWeatherState.value.data!!
                        ) {
                            isShowMoreDetailsClicked = it
                        }
                        AnimatedVisibility(
                            visible = !isShowMoreDetailsClicked,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            WeatherForNextDaysSection(
                                futureWeatherState.value.data!!
                            )
                        }
                    }
                } else {
                    ErrorSection {
                        viewModel.updateWeatherData()
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


