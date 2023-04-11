package com.example.presentation.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.location.sections.CurrentCitySection
import com.example.presentation.location.sections.HintsSection
import com.example.presentation.location.sections.SearchSection

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
            }
            .padding(bottom = 60.dp),
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
                CurrentCitySection(city = currentCity.value.data ?: "")
            }
            AnimatedVisibility(visible = isSearchBarFocused) {
                HintsSection(hintsDto = listOfHintsDtoState.value) {
                    viewModel.saveCurrentCity(it)
                    viewModel.updateCurrentCityState()
                    isSearchBarFocused = !isSearchBarFocused
                }
            }
        }
    }
}

@Preview
@Composable
private fun LocationScreenPreview() {
    LocationScreen()
}