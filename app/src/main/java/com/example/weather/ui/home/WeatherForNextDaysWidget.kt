package com.example.weather.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.api.model.CurrentWeatherDto

@Composable
fun WeatherForNextDaysWidget() {
    Column {
      DaysOfWeatherSection()
      ListOfWeatherSection()
    }

}

@Composable
private fun DaysOfWeatherSection() {
  Row(
    modifier = Modifier.padding(start = 20.dp)
  ) {
    var isTodayChecked by remember { mutableStateOf(true) }
    var isTomorrowChecked by remember { mutableStateOf(false) }
    var isForNextWeek by remember { mutableStateOf(false) }
    Text(
      modifier = Modifier.clickable {
        isTodayChecked = true
        isTomorrowChecked = false
        isForNextWeek = false
      },
      text = stringResource(R.string.today),
      style = TextStyle(
        color = if (isTodayChecked) colorResource(id = R.color.dark_yellow) else Color.White,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 24.sp
      )
    )
    Text(
      modifier = Modifier
        .clickable {
          isTodayChecked = false
          isTomorrowChecked = true
          isForNextWeek = false
        }
        .padding(horizontal = 25.dp),
      text = stringResource(R.string.tomorrow),
      style = TextStyle(
        color = if (isTomorrowChecked) colorResource(id = R.color.dark_yellow) else Color.White,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 24.sp
      )
    )
    Text(
      modifier = Modifier.clickable {
        isTodayChecked = false
        isTomorrowChecked = false
        isForNextWeek = true
      },
      text = stringResource(R.string.next_week),
      style = TextStyle(
        color = if (isForNextWeek) colorResource(id = R.color.dark_yellow) else Color.White,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 24.sp
      )
    )
  }
}

@Composable
private fun SmallWeatherItem(todayWeatherDto: CurrentWeatherDto) {
  Surface(
    modifier = Modifier
      .size(135.dp, 150.dp),
    shape = RoundedCornerShape(25.dp),
    color = Color(0xFF27254A)
  ) {
    Column {
      Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
      ) {
        Image(
          modifier = Modifier
            .size(80.dp),
          painter = painterResource(R.drawable.d50),
          contentDescription = null,
          contentScale = ContentScale.Crop
        )
      }
      Text(
        modifier = Modifier.padding(start = 20.dp),
        text = "time test",
        style = TextStyle(
          color = Color.White,
          fontSize = 12.sp,
          letterSpacing = 1.sp,
          fontFamily = FontFamily(Font(R.font.fabrik))
        )
      )
      Text(
        text = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = Color.White,
              fontWeight = FontWeight.W600,
              fontSize = 30.sp,
              letterSpacing = 1.sp,
              fontFamily = FontFamily(Font(R.font.fabrik))
            )
          ) {
            append("test")
          }
          withStyle(
            style = SpanStyle(
              color = colorResource(id = R.color.dark_yellow),
              fontSize = 14.sp,
              baselineShift = BaselineShift(0.8f),
              fontWeight = FontWeight.ExtraBold,
              fontFamily = FontFamily(Font(R.font.fabrik)),
              letterSpacing = 1.sp
            )
          ) {
            append(" ")
            append(stringResource(id = R.string.degrees_celsius))
          }
        },
        modifier = Modifier.padding(start = 20.dp)
      )
    }
  }
}

@Composable
private fun ListOfWeatherSection(listOfWeatherDto: List<CurrentWeatherDto>? = null) {
  LazyRow(
    contentPadding = PaddingValues(20.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(listOfWeatherDto ?: emptyList()) {
      SmallWeatherItem(it)
    }
  }
}

@Preview
@Composable
fun PreviewWeatherForNextDaysWidget() {
  WeatherForNextDaysWidget()
}

@Preview
@Composable
fun PreviewSmallWeatherItem() {
  SmallWeatherItem(
    CurrentWeatherDto(
    )
  )
}