package com.example.weather.ui.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.weather.ui.location.views.SearchSection

@Composable
fun LocationScreen() {
  var isSearchBarFocused by remember {
    mutableStateOf(false)
  }
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .clickable {
        isSearchBarFocused = false
      },
    color = MaterialTheme.colorScheme.background
  ) {
    SearchSection(
      isSearchBarFocused = isSearchBarFocused,
      onCancelClicked = {
        isSearchBarFocused = false
      },
      focusOfSearchBarWasChanged = {
        isSearchBarFocused = it
      }
    )
  }
}