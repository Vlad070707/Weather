package com.example.presentation.location.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun CurrentCityView(
    modifier: Modifier = Modifier,
    city: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_blue)
        )
    ) {
        if (city.isEmpty()) {
            NoSavedLocationView()
        } else {
            LocationView(city = city)
        }
    }

}

@Composable
private fun NoSavedLocationView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 25.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(60.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.no_location),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.dark_yellow))
        )
        Text(
            modifier = Modifier
                .padding(
                    top = 25.dp
                ),
            text = stringResource(R.string.no_saved_city),
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.fabrik)),
                letterSpacing = 0.5.sp,
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LocationView(city: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 25.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(60.dp),
            imageVector = Icons.Outlined.LocationOn,
            tint = colorResource(id = R.color.dark_yellow),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 10.dp,
                    vertical = 25.dp
                )
                .fillMaxWidth(),
            text = buildAnnotatedString {
                append(stringResource(R.string.your_saved_city_is))
                withStyle(
                    style = SpanStyle(
                        fontSize = 36.sp,
                        color = colorResource(id = R.color.dark_yellow)
                    )
                ) {
                    append(city)
                }
            },
            style = TextStyle(
                color = Color.White,
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.fabrik)),
                letterSpacing = 0.5.sp,
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewCurrentCityView() {
    CurrentCityView(city = "")
}

@Preview
@Composable
fun PreviewNoSavedLocationView() {
    NoSavedLocationView()
}

@Preview
@Composable
fun PreviewLocationView() {
    LocationView("Kyiv")
}