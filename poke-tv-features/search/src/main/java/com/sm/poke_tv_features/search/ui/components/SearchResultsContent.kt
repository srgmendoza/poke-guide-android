package com.sm.poke_tv_features.search.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import coil3.compose.AsyncImage
import com.sm.poke_domain.models.PokeDetailDomainModel

@Composable
fun SearchResultsContent(domainModel: PokeDetailDomainModel, onItemClicked: (String) -> Unit) {
    Column {
        Box {
            AsyncImage(
                model = domainModel.imageUrl,
                contentDescription = "${domainModel.name} search result image",
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .clickable(role = Role.Button) { onItemClicked(domainModel.id) },
                contentScale = ContentScale.Inside
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = "Related Pokemons")
        Spacer(modifier = Modifier.width(16.dp))
        LazyRow {
            items(domainModel.relatedPokemons) { type ->
                RelatedRibbonItems(
                    image = type.imageUrl,
                    name = type.name,
                    onItemClicked = { onItemClicked(type.id) }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun RelatedRibbonItems(image: String?, name: String, onItemClicked: () -> Unit) {
    Column {
        Box(Modifier.clickable(role = Role.Button) { onItemClicked() }) {
            AsyncImage(
                model = image,
                contentDescription = "$name related image",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Inside
            )
            Text(text = name)
        }
    }
}