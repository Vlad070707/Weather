package com.example.weather.ui.location.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.home.views.TitleSection

@Composable
fun SearchSection(
  isSearchBarFocused:Boolean,
  onCancelClicked: () -> Unit,
  focusOfSearchBarWasChanged: (Boolean) -> Unit
) {
  val focusManager = LocalFocusManager.current

  if (!isSearchBarFocused) {
    focusManager.clearFocus()
  }

//  val topPadding by animateDpAsState(
//    if (!isSearchBarFocused) 40.dp else 15.dp,
////    animationSpec = spring(
////      dampingRatio = Spring.DampingRatioLowBouncy,
////      stiffness = Spring.StiffnessHigh
////    )
//  )

  Column(
    modifier = Modifier
      .padding(top = 20.dp)
  ) {
    AnimatedVisibility(visible = !isSearchBarFocused) {
      TitleSection(
        modifier = Modifier
          .fillMaxWidth()
      )
    }
    SearchBarView(
      isSearchBarFocused,
      focusOfSearchBarWasChanged = focusOfSearchBarWasChanged,
      onCancelClicked = onCancelClicked
    )
  }
}

@Composable
private fun SearchBarView(
  isSearchBarFocused: Boolean,
  focusOfSearchBarWasChanged: (Boolean) -> Unit,
  onCancelClicked: () -> Unit
) {
  var text by remember {
    mutableStateOf(TextFieldValue(""))
  }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp)
      .padding(top = if (isSearchBarFocused) 0.dp else 20.dp)
      .background(Color.Transparent),
    verticalAlignment = Alignment.CenterVertically
  ) {
    TextField(
      modifier = Modifier
        .onFocusChanged {
          focusOfSearchBarWasChanged(it.isFocused)
        }
        .weight(1f),
      value = text,
      leadingIcon = {
        Icon(
          imageVector = Icons.Outlined.Search,
          contentDescription = null,
          tint = colorResource(id = R.color.dark_yellow)
        )
      },
      onValueChange = { newText ->
        text = newText
      },
      placeholder = {
        Text(
          text = "Search your city",
          style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.fabrik)),
            letterSpacing = 0.5.sp,
          )
        )
      },
      singleLine = true,
      shape = RoundedCornerShape(25.dp),
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        cursorColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        containerColor = colorResource(id = R.color.dark_blue)
      )
    )
    AnimatedVisibility(visible = isSearchBarFocused) {
      Text(
        modifier = Modifier
          .clickable {
            onCancelClicked()
            text = TextFieldValue("")
          }
          .weight(1f)
          .padding(start = 15.dp)
          .animateContentSize(),
        text = "Cancel",
        style = TextStyle(
          color = colorResource(id = R.color.dark_yellow),
          fontSize = 20.sp,
          fontFamily = FontFamily(Font(R.font.fabrik)),
        )
      )
    }
  }
}

@Preview
@Composable
fun PreviewSearchSection() {
//  SearchSection()
}
