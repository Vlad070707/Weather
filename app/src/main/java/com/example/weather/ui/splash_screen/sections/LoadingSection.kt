package com.example.weather.ui.splash_screen.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.ui.home.sections.TitleSection

@Composable
fun LoadingSection() {
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