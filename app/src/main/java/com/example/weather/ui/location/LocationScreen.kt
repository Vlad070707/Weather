package com.example.weather.ui.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.ui.location.views.CurrentCitySection
import com.example.weather.ui.location.views.SearchSection
import com.example.weather.util.Resource

@Composable
fun LocationScreen(
  viewModel: LocationViewModel = hiltViewModel()
) {
  var isSearchBarFocused by remember {
    mutableStateOf(false)
  }
  val listOfHintsDtoState = viewModel.listOfHintsDtoState.collectAsState()
  val currentCity = viewModel.currentCityState.collectAsState()

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
        when (listOfHintsDtoState.value) {
          is Resource.Loading -> {
            CircularProgressIndicator(
              modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize(align = Alignment.Center),
              color = colorResource(id = R.color.dark_yellow)
            )
          }
          else -> {
            listOfHintsDtoState.value.data?.geonames?.let { it ->
              LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                items(it) { geoname ->
                  geoname?.toponymName?.let { it1 ->
                    Text(
                      modifier = Modifier
                        .clickable {
                          viewModel.saveCurrentCity(it1)
                          isSearchBarFocused = !isSearchBarFocused
                        },
                      text = it1,
                      style = TextStyle(
                        color = colorResource(id = R.color.dark_yellow),
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.fabrik)),
                        letterSpacing = 0.5.sp,
                      )
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}