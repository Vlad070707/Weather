package com.example.presentation.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun ErrorSection(
  onUpdateButtonClicked: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      modifier = Modifier
        .size(100.dp),
      alignment = Alignment.Center,
      painter = painterResource(id = R.drawable.error_icon),
      contentDescription = null,
      colorFilter = ColorFilter.tint(color = colorResource(id = R.color.dark_yellow))
    )
    Text(
      modifier = Modifier
        .padding(vertical = 30.dp),
      text = stringResource(R.string.error_message),
      style = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = 1.sp,
        fontFamily = FontFamily(Font(R.font.fabrik))
      ),
      textAlign = TextAlign.Center
    )
    Button(
      onClick = onUpdateButtonClicked,
      colors = ButtonDefaults.textButtonColors(
        containerColor = colorResource(id = R.color.dark_yellow)
      ),
      shape = RoundedCornerShape(25.dp)
    ) {
      Text(
        text = stringResource(R.string.try_again),
        style = TextStyle(
          color = MaterialTheme.colorScheme.background,
          fontWeight = FontWeight.SemiBold,
          fontSize = 30.sp,
          letterSpacing = 1.sp,
          fontFamily = FontFamily(Font(R.font.fabrik))
        )
      )
    }
  }
}