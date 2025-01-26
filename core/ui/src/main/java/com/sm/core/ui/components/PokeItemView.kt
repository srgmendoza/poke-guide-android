package com.sm.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun PokeItemView(
    modifier: Modifier = Modifier,
    pokeName: String,
    pokeImageUrl: String?,
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
        Column {
            AsyncImage(
                model = pokeImageUrl,
                //placeholder = painterResource(id = R.drawable.marvel_placeholder),
                contentDescription = "$pokeName image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(12.dp),
                text = pokeName
            )
        }
    }
}