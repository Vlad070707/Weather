package com.example.weather.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun TitleWidget() {
  Box(
    modifier = Modifier
      .padding(top = 20.dp)
      .fillMaxWidth(),
    contentAlignment = Alignment.CenterStart
  ) {
    Icon(
      modifier = Modifier.padding(start = 30.dp),
      imageVector = Icons.Filled.Settings,
      tint = Color.White,
      contentDescription = null
    )
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = stringResource(id = R.string.title),
      textAlign = TextAlign.Center,
      style = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        fontSize = 30.sp,
        letterSpacing = 1.5.sp
      )
    )
  }
}

@Preview
@Composable
fun PreviewTitleWidget() {
  TitleWidget()
}