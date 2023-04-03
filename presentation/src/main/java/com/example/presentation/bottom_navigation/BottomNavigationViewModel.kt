package com.example.presentation.bottom_navigation

import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    getSavedLocationUseCase: GetSavedLocationUseCase
) : BaseViewModel(getSavedLocationUseCase)