package com.example.weather.ui.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.ui.location.views.SearchSection

@Composable
fun LocationScreen(
  viewModel: LocationViewModel = hiltViewModel()
) {
  var isSearchBarFocused by remember {
    mutableStateOf(false)
  }
  val searchCityState = viewModel.searchCityState.collectAsState()

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
      searchCityState.value.data?.geonames?.let { it ->
        LazyColumn(
          contentPadding = PaddingValues(20.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(it) { geoname ->
            geoname?.toponymName?.let { it1 ->
              Text(
                modifier = Modifier
                  .clickable {

                  },
                text = it1,
                color = Color.White
              )
            }
          }
        }
      }
    }

  }
}