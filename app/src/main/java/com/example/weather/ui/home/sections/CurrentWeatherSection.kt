package com.example.weather.ui.home.sections

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
import com.example.weather.data.api.weather_models.CurrentWeatherDto
import com.example.weather.data.api.weather_models.Main
import com.example.weather.data.api.weather_models.Weather
import com.example.weather.util.Utils
import java.util.*
import kotlin.math.roundToInt

@SuppressLint("RememberReturnType")
@Composable
fun CurrentWeatherSection(
  todayWeatherDto: CurrentWeatherDto
) {
  var showMoreDetails by remember {
    mutableStateOf(false)
  }

  val rotation = remember {
    Animatable(initialValue = 0f)
  }

  LaunchedEffect(showMoreDetails) {
    rotation.animateTo(
      targetValue = if (showMoreDetails) 180f else 0f,
      animationSpec = tween(durationMillis = 800),
    )
  }

  Surface(
    modifier = Modifier
      .padding(20.dp)
      .animateContentSize(
        animationSpec = spring(
          dampingRatio = Spring.DampingRatioMediumBouncy,
          stiffness = Spring.StiffnessMediumLow
        )
      ),
    shape = RoundedCornerShape(25.dp),
    color = colorResource(id = R.color.dark_blue)
  ) {
    Column(
      modifier = Modifier
        .padding(top = 25.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        CurrentDateView()
        DescriptionView(weatherDto = todayWeatherDto)
        CurrentTempView(weatherDto = todayWeatherDto)
        AnimatedVisibility(visible = showMoreDetails) {
          AdditionalInfoView(weatherDto = todayWeatherDto)
        }
        val modifierForLocationView = if (showMoreDetails) {
          Modifier
            .padding(
              start = 30.dp,
              top = 10.dp
            )
        } else {
          Modifier
            .padding(start = 30.dp)
        }
        CurrentLocationView(
          modifier = modifierForLocationView,
          weatherDto = todayWeatherDto
        )
      }
      Icon(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally)
          .clickable {
            showMoreDetails = !showMoreDetails
          }
          .size(60.dp)
          .rotate(rotation.value),
        imageVector = Icons.Outlined.KeyboardArrowDown,
        contentDescription = null,
        tint = colorResource(id = R.color.dark_yellow)
      )
    }
  }
}

@Composable
fun CurrentDateView() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 30.dp, end = 30.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = stringResource(R.string.now),
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
      .fillMaxWidth()
      .padding(start = 30.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    CurrentTempText(
      temp = weatherDto.main?.temp?.roundToInt().toString(),
      textSize = 66
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
    modifier = Modifier.padding(start = 30.dp, top = 10.dp),
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
  modifier: Modifier = Modifier,
  weatherDto: CurrentWeatherDto
) {
  Row(
    modifier = modifier,
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

@Composable
private fun AdditionalInfoView(weatherDto: CurrentWeatherDto) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 30.dp)
  ) {
    Row(
      verticalAlignment = Alignment.Bottom
    ) {
      Text(
        text = "Feels like: ",
        style = TextStyle(
          color = colorResource(id = R.color.dark_yellow),
          fontFamily = FontFamily(Font(R.font.fabrik)),
          letterSpacing = 0.5.sp,
          fontSize = 20.sp
        )
      )
      CurrentTempText(
        temp = weatherDto.main?.feelsLike?.roundToInt().toString(),
        textSize = 24
      )
    }
    val atmosphericPressure = weatherDto.main?.pressure.toString()
    RowOfAdditionalInfo(
      modifier = Modifier
        .padding(top = 10.dp),
      description = "Atmospheric pressure: ",
      value = "$atmosphericPressure hPa"
    )
    val humidity = weatherDto.main?.humidity.toString()
    RowOfAdditionalInfo(
      modifier = Modifier
        .padding(top = 10.dp),
      description = "Humidity: ",
      value = "$humidity %"
    )
    val windSpeed = weatherDto.wind?.speed.toString()
    RowOfAdditionalInfo(
      modifier = Modifier
        .padding(top = 10.dp),
      description = "Wind speed: ",
      value = "$windSpeed meter/sec"
    )
    val cloudiness = weatherDto.clouds?.all.toString()
    RowOfAdditionalInfo(
      modifier = Modifier
        .padding(top = 10.dp),
      description = "Cloudiness: ",
      value = "$cloudiness %"
    )
  }
}

@Composable
private fun RowOfAdditionalInfo(
  modifier: Modifier = Modifier,
  description: String,
  value: String
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.Bottom
  ) {
    Text(
      text = description,
      style = TextStyle(
        color = colorResource(id = R.color.dark_yellow),
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 20.sp
      )
    )
    Text(
      text = value,
      style = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 20.sp
      )
    )
  }
}

@Composable
private fun CurrentTempText(temp: String, textSize: Int) {
  Text(
    buildAnnotatedString {
      withStyle(
        style = SpanStyle(
          color = Color.White,
          fontWeight = FontWeight.W600,
          fontSize = textSize.sp,
          letterSpacing = 1.sp,
          fontFamily = FontFamily(Font(R.font.fabrik))
        )
      ) {
        append(temp)
      }
      withStyle(
        style = SpanStyle(
          color = colorResource(id = R.color.dark_yellow),
          // "°C" should be half the size of the temp text
          fontSize = (textSize / 2).sp,
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
    weatherDto = CurrentWeatherDto(
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