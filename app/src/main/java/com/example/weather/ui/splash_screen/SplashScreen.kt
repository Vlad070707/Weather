package com.example.weather.ui.splash_screen

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weather.R
import com.example.weather.ui.bottom_navigation.Screen
import com.example.weather.ui.home.views.TitleSection
import kotlinx.coroutines.delay

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SplashScreen(
  navController: NavHostController
) {
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

    val scale = remember {
      Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
      scale.animateTo(
        targetValue = 0.3f,
        animationSpec = tween(
          durationMillis = 500,
          easing = {
            OvershootInterpolator(2f).getInterpolation(it)
          })
      )
      delay(3000)
      navController.navigate(Screen.Home.route) {
        popUpTo(0)
      }
    }
  }
}