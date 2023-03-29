package com.example.presentation.home.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun TitleSection(
  modifier: Modifier
) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
  ) {
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
fun PreviewTitleSection() {
  TitleSection(Modifier)
}