package com.example.weather.ui.splash_screen

import com.example.weather.data.repository.UserPreferencesRepository
import com.example.weather.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
  userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel(userPreferencesRepository)