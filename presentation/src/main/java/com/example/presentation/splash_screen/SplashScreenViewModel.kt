package com.example.presentation.splash_screen

import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    getSavedLocationUseCase: GetSavedLocationUseCase
) : BaseViewModel(getSavedLocationUseCase)