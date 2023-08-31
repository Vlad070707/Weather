package com.example.presentation.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.location.view.CurrentCityView
import com.example.presentation.location.view.HintsView
import com.example.presentation.location.view.SearchView

@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .padding(bottom = 60.dp)
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .clickable {
                viewModel.clearFocusOnSearchBar()
            }
            .background(color = colorResource(id = R.color.light_blue))) {
            Column {
                SearchView(
                    isSearchBarFocused = uiState.value.isSearchBarFocused,
                    searchBarText = uiState.value.query,
                    onCancelClicked = {
                        viewModel.clearFocusOnSearchBar()
                    },
                    focusOfSearchBarWasChanged = {
                        viewModel.updateSearchBarFocusStatus(it)
                    },
                    onSearchValueChange = {
                        viewModel.searchCity(it)
                    }
                )
                AnimatedVisibility(visible = !uiState.value.isSearchBarFocused) {
                    CurrentCityView(city = uiState.value.currentCity)
                }
                AnimatedVisibility(visible = uiState.value.isSearchBarFocused) {
                    HintsView(
                        hintsList = uiState.value.hintsList,
                        isLoading = uiState.value.isLoading,
                        onHintClicked = {
                            viewModel.updateCurrentCity(it)
                        }
                    )
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