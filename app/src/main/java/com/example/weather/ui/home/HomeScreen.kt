package com.example.weather.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.R
import com.example.weather.util.Resource

@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel()
) {

  val state = viewModel.state.collectAsState()

  when (state.value) {
    is Resource.Success -> {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xFF11103A))
      ) {
        TitleWidget()
        CurrentWeatherWidget(
          state.value.data!!
        )
        WeatherForNextDaysWidget()
      }
    }
    is Resource.Error -> {
      TODO()
    }
    is Resource.Loading -> {
      CircularProgressIndicator(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xFF11103A))
          .wrapContentSize(align = Alignment.Center),
        color = colorResource(id = R.color.dark_yellow)
      )
    }
  }

}

@Preview
@Composable
fun PreviewHomeScreen() {
  HomeScreen()
}


