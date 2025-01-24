package com.sm.poke_features.listing.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoaderView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
        )
    }
}