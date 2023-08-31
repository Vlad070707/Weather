package com.example.presentation.location.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.home.sections.TitleSection

@Composable
fun SearchView(
    isSearchBarFocused: Boolean,
    searchBarText: String,
    onCancelClicked: () -> Unit,
    focusOfSearchBarWasChanged: (Boolean) -> Unit,
    onSearchValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    if (!isSearchBarFocused) {
        focusManager.clearFocus()
    }

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
            isSearchBarFocused = isSearchBarFocused,
            searchBarText = searchBarText,
            focusOfSearchBarWasChanged = focusOfSearchBarWasChanged,
            onCancelClicked = onCancelClicked,
            onSearchValueChange = onSearchValueChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarView(
    isSearchBarFocused: Boolean,
    searchBarText: String,
    focusOfSearchBarWasChanged: (Boolean) -> Unit,
    onCancelClicked: () -> Unit,
    onSearchValueChange: (String) -> Unit
) {
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
            value = searchBarText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = colorResource(id = R.color.dark_yellow)
                )
            },
            onValueChange = onSearchValueChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_your_city),
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
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
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
fun PreviewSearchView() {
//    SearchView()
}
