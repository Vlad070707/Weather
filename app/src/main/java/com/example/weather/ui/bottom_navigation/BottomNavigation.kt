package com.example.weather.ui.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.R
import com.example.weather.util.Screen

@Composable
fun BottomNavigation(
  navController: NavController,
  viewModel: BottomNavigationViewModel = hiltViewModel()
) {
  val currentCityState = viewModel.currentCityState.collectAsState()

  var currentScreen = when (navController.currentBackStackEntryAsState().value?.destination?.route) {
    Screen.Location().route -> Screen.Location()
    Screen.Home().route -> Screen.Home()
    else -> null
  }

  val isHomeAvailable = currentCityState.value.isNotEmpty()

  val homeScreen = Screen.Home().apply {
    if (!isHomeAvailable){
      isAvailable = false
    }
  }
  val items = listOf(homeScreen, Screen.Location())

  currentScreen?.let { screen ->
    Row(
      modifier = Modifier
        .background(colorResource(id = R.color.dark_blue))
        .padding(10.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      items.forEach { item ->
        BottomNavigationItem(item = item, isSelected = item.route == screen.route, isEnabled = item.isAvailable) {
          currentScreen = item
          navController.navigate(item.route)
        }
      }
    }
  }
}

@Composable
fun BottomNavigationItem(item: Screen, isSelected: Boolean, isEnabled: Boolean = true, onClick: () -> Unit) {
  val background = if (isSelected) colorResource(id = R.color.dark_yellow) else Color.Transparent
  val contentColor = when {
    isSelected -> MaterialTheme.colorScheme.background
    !isEnabled -> Color.Gray
    else -> colorResource(id = R.color.dark_yellow)
  }
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(25.dp))
      .background(background)
      .clickable(onClick = {
        if (isEnabled) {
          onClick()
        }
      })
      .padding(horizontal = 15.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      item.icon?.let {
        Icon(
          imageVector = it,
          contentDescription = null,
          tint = contentColor
        )
      }
      Text(
        text = item.title,
        style = TextStyle(
          color = contentColor,
          fontWeight = FontWeight.W600,
          fontSize = 20.sp,
          letterSpacing = 1.sp,
          fontFamily = FontFamily(Font(R.font.fabrik))
        )
      )
    }
  }
}

@Preview
@Composable
fun PreviewBottomNavigation() {
  BottomNavigation(rememberNavController())
}

@Preview
@Composable
fun PreviewBottomNavigationItem() {
//  BottomNavigationItem(item = Screen.Home, isSelected = true) { }
}