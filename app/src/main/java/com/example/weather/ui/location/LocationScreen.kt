package com.example.weather.ui.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.ui.location.sections.CurrentCitySection
import com.example.weather.ui.location.sections.HintsSection
import com.example.weather.ui.location.sections.SearchSection

@Composable
fun LocationScreen(
  viewModel: LocationViewModel = hiltViewModel()
) {
  val listOfHintsDtoState = viewModel.listOfHintsDtoState.collectAsState()
  val currentCity = viewModel.currentCityState.collectAsState()
  var isSearchBarFocused by remember {
    mutableStateOf(false)
  }

  if (!isSearchBarFocused) {
    viewModel.clearListOfHints()
  }

  Surface(
    modifier = Modifier
      .fillMaxSize()
      .clickable {
        isSearchBarFocused = false
      },
    color = MaterialTheme.colorScheme.background
  ) {
    Column {
      SearchSection(
        isSearchBarFocused = isSearchBarFocused,
        onCancelClicked = {
          isSearchBarFocused = false
        },
        focusOfSearchBarWasChanged = {
          isSearchBarFocused = it
        },
        searchCity = {
          viewModel.searchCity(it)
        }
      )
      AnimatedVisibility(visible = !isSearchBarFocused) {
        CurrentCitySection(city = currentCity.value)
      }
      AnimatedVisibility(visible = isSearchBarFocused) {
        HintsSection(hintsDto = listOfHintsDtoState.value) {
          viewModel.saveCurrentCity(it)
          isSearchBarFocused = !isSearchBarFocused
        }
      }
    }
  }
}