package com.example.weather.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.api.models.CurrentWeatherDto
import com.example.weather.data.api.models.Main
import com.example.weather.data.api.models.Weather
import com.example.weather.util.Utils
import java.util.*
import kotlin.math.roundToInt

@Composable
fun CurrentWeatherSection(
  todayWeatherDto: CurrentWeatherDto
) {
  Surface(
    modifier = Modifier
      .padding(20.dp),
    shape = RoundedCornerShape(25.dp),
    color = colorResource(id = R.color.dark_blue)
  ) {
    Column(
      modifier = Modifier
        .padding(start = 30.dp, top = 25.dp, bottom = 25.dp)
    ) {
      CurrentDateView()
      DescriptionView(weatherDto = todayWeatherDto)
      CurrentTempView(weatherDto = todayWeatherDto)
      CurrentLocationView(weatherDto = todayWeatherDto)
    }
  }
}

@Composable
fun CurrentDateView() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(end = 30.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = stringResource(R.string.today),
      style = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = 1.sp,
        fontFamily = FontFamily(Font(R.font.fabrik))
      )
    )
    Text(
      text = Utils.getDateFor(pattern = "EE, dd MMM"),
      style = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
      )
    )
  }
}

@Composable
fun CurrentTempView(
  weatherDto: CurrentWeatherDto
) {
  Row(
    modifier = Modifier
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            color = Color.White,
            fontWeight = FontWeight.W600,
            fontSize = 66.sp,
            letterSpacing = 1.sp,
            fontFamily = FontFamily(Font(R.font.fabrik))
          )
        ) {
          append(weatherDto.main?.temp?.roundToInt().toString())
        }
        withStyle(
          style = SpanStyle(
            color = colorResource(id = R.color.dark_yellow),
            fontSize = 32.sp,
            baselineShift = BaselineShift(1.4f),
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(R.font.fabrik)),
            letterSpacing = 1.sp
          )
        ) {
          append(" ")
          append(stringResource(id = R.string.degrees_celsius))
        }
      }
    )
    val image = Utils.getImage(weatherDto.weather?.get(0)?.icon)
    Image(
      modifier = Modifier
        .size(150.dp),
      painter = painterResource(image),
      contentDescription = null,
      contentScale = ContentScale.Crop
    )
  }
}

@Composable
fun DescriptionView(
  weatherDto: CurrentWeatherDto
) {
  Row(
    modifier = Modifier.padding(top = 10.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = weatherDto.weather?.get(0)?.description.toString()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
      style = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
      )
    )
  }
}

@Composable
fun CurrentLocationView(
  weatherDto: CurrentWeatherDto
) {
  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Outlined.LocationOn,
      tint = colorResource(id = R.color.dark_yellow),
      contentDescription = null
    )
    Text(
      modifier = Modifier.padding(start = 10.dp),
      text = weatherDto.name.toString(),
      style = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
      )
    )
  }
}

@Preview
@Composable
fun PreviewCurrentDateView() {
  CurrentDateView()
}

@Preview
@Composable
fun PreviewCurrentTempView() {
  CurrentTempView(
    CurrentWeatherDto(
      main = Main(temp = 30.0),
      weather = listOf(Weather(icon = "01d"))
    )
  )
}

@Preview
@Composable
fun PreviewDescriptionView() {
  DescriptionView(
    CurrentWeatherDto(
      weather = listOf(Weather(description = "overcast clouds"))
    )
  )
}

@Preview
@Composable
fun PreviewCurrentLocationView() {
  CurrentLocationView(
    CurrentWeatherDto(
      name = "Allentown, New Mexico 31134"
    )
  )
}

@Preview
@Composable
fun PreviewCurrentWeatherSection() {
  CurrentWeatherSection(
    CurrentWeatherDto(
      main = Main(temp = 30.0),
      name = "Allentown, New Mexico 31134",
      weather = listOf(Weather(icon = "01d", description = "overcast clouds"))
    )
  )
}