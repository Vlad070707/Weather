package com.example.weather.ui.location.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.api.city_models.ListOfHintsDto
import com.example.weather.util.Resource
import java.util.*

@Composable
fun HintsSection(
  hintsDto: Resource<ListOfHintsDto>,
  onHintClicked: (String) -> Unit
) {
  when (hintsDto) {
    is Resource.Loading<*> -> {
      CircularProgressIndicator(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.background)
          .wrapContentSize(align = Alignment.Center)
          .padding(top = 30.dp),
        color = colorResource(id = R.color.dark_yellow)
      )
    }
    else -> {
      hintsDto.data?.geonames?.let { geonamesList ->
        LazyColumn(
          contentPadding = PaddingValues(20.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(geonamesList) { geoname ->
            geoname?.name?.let { name ->
              val countryCode = geoname.countryCode
              HintItem(
                name = name,
                countryCode = countryCode,
                onHintClicked = onHintClicked
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun HintItem(
  name: String,
  countryCode: String?,
  onHintClicked: (String) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      modifier = Modifier
        .clickable {
          onHintClicked(name)
        },
      text = name,
      style = TextStyle(
        color = colorResource(id = R.color.dark_yellow),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.fabrik)),
        letterSpacing = 0.5.sp,
      )
    )
    countryCode?.let {
      Text(
        text = countryCodeToEmojiFlag(it),
        fontSize = 22.sp,
      )
    }
  }
}

fun countryCodeToEmojiFlag(countryCode: String): String {
  return countryCode
    .uppercase(Locale.getDefault())
    .map { char ->
      Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
    }
    .map { codePoint ->
      Character.toChars(codePoint)
    }
    .joinToString(separator = "") { charArray ->
      String(charArray)
    }
}