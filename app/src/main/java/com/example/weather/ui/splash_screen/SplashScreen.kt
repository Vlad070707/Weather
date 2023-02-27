package com.example.weather.ui.splash_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weather.R
import com.example.weather.ui.bottom_navigation.Screen
import com.example.weather.ui.home.sections.TitleSection
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  navController: NavHostController,
  viewModel: SplashScreenViewModel = hiltViewModel()
) {

  val savedCity = viewModel.currentCityState.collectAsState()
  LoadingView()
  LaunchedEffect(true) {
    delay(3000)
    if (savedCity.value.isNotEmpty()) {
      Log.i("myLogs", "SplashScreen: NAVIGATE TO HOME SCREEN")
      navController.navigate(Screen.Home.route) {
        popUpTo(0)
      }
    } else {
      Log.i("myLogs", "SplashScreen: NAVIGATE TO LOCATION SCREEN")
      navController.navigate(Screen.Location.route) {
        popUpTo(0)
      }
    }
  }


}

@Composable
private fun LoadingView() {
  Column(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      modifier = Modifier
        .size(250.dp),
      painter = painterResource(R.drawable.d02),
      contentDescription = null,
      contentScale = ContentScale.Crop
    )
    TitleSection(
      modifier = Modifier
        .padding(top = 100.dp)
    )
    CircularProgressIndicator(
      modifier = Modifier
        .padding(20.dp),
      color = colorResource(id = R.color.dark_yellow)
    )
  }
}