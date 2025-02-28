package com.sm.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun PokeItemView(
    modifier: Modifier = Modifier,
    pokeName: String,
    pokeImageUrl: String?,
    contentScale: ContentScale = ContentScale.Fit,
    onClick: (String) -> Unit
) {
    val paddingModifier = modifier
        .padding(8.dp)
        .fillMaxWidth()

    Card(
        modifier = paddingModifier
            .clickable(
                role = Role.Button,
                onClick = {
                    onClick(pokeName)
                }
            )
    ) {
        Box {
            AsyncImage(
                model = pokeImageUrl,
                //placeholder = painterResource(id = R.drawable.marvel_placeholder),
                contentDescription = "$pokeName image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = contentScale
            )
            Text(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                text = pokeName
            )
        }
    }
}