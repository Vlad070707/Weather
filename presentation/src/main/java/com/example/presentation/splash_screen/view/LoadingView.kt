package com.example.presentation.splash_screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.base.Loader
import com.example.presentation.home.view.TitleView

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(250.dp),
            painter = painterResource(R.drawable.d02),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        TitleView(
            modifier = Modifier
                .padding(top = 100.dp)
        )
        Loader(
            modifier = Modifier
                .size(100.dp)
        )
    }
}