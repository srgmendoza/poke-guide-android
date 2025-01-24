package com.sm.poke_features.listing.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.sm.core.ui.components.PokeLoaderView

@Composable
fun ListingScreen(viewModel: ListingScreenViewModel) {

    val viewState = viewModel.viewState.collectAsState()

    when (viewState.value) {
        is ListingScreenViewState.Initial -> {
            viewModel.fetchContent()
        }

        is ListingScreenViewState.Loading -> {
            PokeLoaderView()
        }

        is ListingScreenViewState.Error -> {
            Text("Error")
        }

        else -> {
            LazyColumn (verticalArrangement = Arrangement.SpaceBetween) {
                viewState.value.form.domainModel.map {
                    item {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = it.imageUrl,
                                contentDescription = null,
                            )
                            Text(it.name)
                        }
                    }
                }
            }
        }
    }
}

