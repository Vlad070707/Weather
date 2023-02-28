package com.example.weather.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.ui.home.sections.CurrentWeatherSection
import com.example.weather.ui.home.sections.ErrorSection
import com.example.weather.ui.home.sections.WeatherForNextDaysSection
import com.example.weather.util.Resource

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

  when (isLoading) {
    true -> {
      CircularProgressIndicator(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
          .wrapContentSize(align = Alignment.Center),
        color = colorResource(id = R.color.dark_yellow)
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

@Preview
@Composable
fun PreviewHomeScreen() {
  HomeScreen(hiltViewModel())
}


