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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.api.model.FutureWeatherDto
import com.example.weather.data.api.model.Item
import com.example.weather.data.api.model.Main
import com.example.weather.util.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Composable
fun WeatherForNextDaysWidget(
  futureWeatherDto: FutureWeatherDto
) {
  var isTodayChecked by remember { mutableStateOf(true) }
  var isTomorrowChecked by remember { mutableStateOf(false) }
  Column {
    DaysOfWeatherSection(
      checkToday = { isChecked ->
        isTodayChecked = isChecked
      },
      checkTomorrow = { isChecked ->
        isTomorrowChecked = isChecked
      }
    )
    val list = when {
      isTodayChecked -> Utils.mapWeather(futureWeatherDto = futureWeatherDto, isTodayWeather = true)
      isTomorrowChecked -> Utils.mapWeather(futureWeatherDto = futureWeatherDto, isTodayWeather = false)
      else -> futureWeatherDto.list
    }
    ListOfWeatherSection(list, !isTodayChecked && !isTomorrowChecked)
  }
}

@Composable
private fun DaysOfWeatherSection(
  checkToday: (Boolean) -> Unit,
  checkTomorrow: (Boolean) -> Unit
) {
  var isTodayChecked by remember {
    mutableStateOf(true)
  }
  var isTomorrowChecked by remember {
    mutableStateOf(false)
  }
  var isNextFiveDaysChecked by remember {
    mutableStateOf(false)
  }
  Row(
    modifier = Modifier.padding(start = 20.dp)
  ) {

    Text(
      modifier = Modifier.clickable {
        checkToday(true)
        checkTomorrow(false)
        isTodayChecked = true
        isTomorrowChecked = false
        isNextFiveDaysChecked = false
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
          checkToday(false)
          checkTomorrow(true)
          isTodayChecked = false
          isTomorrowChecked = true
          isNextFiveDaysChecked = false
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
        checkToday(false)
        checkTomorrow(false)
        isTodayChecked = false
        isTomorrowChecked = false
        isNextFiveDaysChecked = true
      },
      text = stringResource(R.string.next_week),
      style = TextStyle(
        color = if (isNextFiveDaysChecked) colorResource(id = R.color.dark_yellow) else Color.White,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
        fontSize = 24.sp
      )
    )
  }
}

@Composable
private fun SmallWeatherItem(item: Item?, showDate: Boolean) {
  val dateAndTime = getDateAndTime(item)
  var formattedDate = dateAndTime.first
  val formattedTime = dateAndTime.second
  when (formattedDate) {
    Utils.getDateFor(pattern = "EE, dd MMM") -> {
      formattedDate = stringResource(id = R.string.today)
    }
    Utils.getDateFor(forTomorrow = true, pattern = "EE, dd MMM") -> {
      formattedDate = stringResource(id = R.string.tomorrow)
    }
  }

  val height = if (showDate) 150.dp else 130.dp
  Surface(
    modifier = Modifier
      .size(140.dp, height),
    shape = RoundedCornerShape(25.dp),
    color = colorResource(id = R.color.dark_blue)
  ) {
    Column(
      modifier = Modifier
        .padding(vertical = 10.dp),
      verticalArrangement = Arrangement.Center
    ) {
      if (showDate) {
        Text(
          modifier = Modifier
            .fillMaxWidth(),
          text = formattedDate,
          style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
            letterSpacing = 1.sp,
            fontFamily = FontFamily(Font(R.font.fabrik))
          ),
          textAlign = TextAlign.Center
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
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
              append(item?.main?.temp?.roundToInt().toString())
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
        )
        val image = Utils.getImage(item?.weather?.get(0)?.icon)
        Image(
          modifier = Modifier
            .size(80.dp),
          painter = painterResource(image),
          contentDescription = null,
          contentScale = ContentScale.Crop
        )
      }

      Text(
        modifier = Modifier.fillMaxWidth(),
        text = formattedTime,
        style = TextStyle(
          color = Color.White,
          fontSize = 18.sp,
          letterSpacing = 1.sp,
          fontFamily = FontFamily(Font(R.font.fabrik))
        ),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun ListOfWeatherSection(futureWeatherDto: List<Item?>, showDate: Boolean) {
  LazyRow(
    contentPadding = PaddingValues(20.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(futureWeatherDto) {
      SmallWeatherItem(it, showDate)
    }
  }
}

/**
 * this function returns pair,
 * where param_1 is date of item and param_2 is time of item
 */
private fun getDateAndTime(item: Item?): Pair<String, String> {
  val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(item?.dtTxt.toString())
  val formattedDate = SimpleDateFormat("EE, dd MMM", Locale.ENGLISH).format(date?.time ?: "")
  val formattedTime = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(date?.time ?: "")
  return Pair(formattedDate, formattedTime)
}

@Preview
@Composable
fun PreviewWeatherForNextDaysWidget() {
  WeatherForNextDaysWidget(
    FutureWeatherDto(
      list = emptyList()
    )
  )
}

@Preview
@Composable
fun PreviewSmallWeatherItem() {
  SmallWeatherItem(
    Item(
      main = Main(temp = -13.0),
      dtTxt = "2023-02-05 15:00:00"
    ),
    true
  )
}