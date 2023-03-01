package com.example.weather.ui.bottom_navigation

import com.example.weather.data.repository.UserPreferencesRepository
import com.example.weather.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
  userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel(userPreferencesRepository)