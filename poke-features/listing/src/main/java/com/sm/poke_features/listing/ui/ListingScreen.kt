package com.sm.poke_features.listing.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.sm.core.ui.components.PokeLoaderView
import com.sm.poke_domain.models.PokemonListItemDomainModel
import kotlinx.coroutines.flow.Flow

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
            ListView(
                pokemons = viewState.value.form,
                onClick = { pokemon ->
                    //TODO. Navigate to detail
                }
            )
        }
    }
}

@Composable
fun ListView(
    pokemons: Flow<PagingData<ListingScreenViewForm>>,
    onClick: (ListingScreenViewForm) -> Unit
) {
    val lazyPagingItems = pokemons.collectAsLazyPagingItems()
    val gridState = rememberLazyGridState()

    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            PokeLoaderView(modifier = Modifier.fillMaxWidth())
        }

        is LoadState.Error -> {
            // item { onErrorItem("Error Refreshing Screen") }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                state = gridState,
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = lazyPagingItems.itemCount,
                    key = lazyPagingItems.itemKey(),
                    contentType = lazyPagingItems.itemContentType()
                ) {
                    val item = lazyPagingItems[it]
                    if (item != null) {
                        ListedItemView(item = item, onClick)
                    }
                }

                // Handle append state
                when (lazyPagingItems.loadState.append) {
                    is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) { // Span the full width
                            PokeLoaderView(modifier = Modifier.fillMaxWidth())
                        }
                    }

                    is LoadState.Error -> {
                        // item { onErrorItem("Error Refreshing Screen") }
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
fun ListedItemView(item: ListingScreenViewForm, onClick: (ListingScreenViewForm) -> Unit) {
    val paddingModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    Card(
        modifier = paddingModifier
            .clickable(
                role = Role.Button,
                onClick = {
                    onClick(item)
                }
            )
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                //placeholder = painterResource(id = R.drawable.marvel_placeholder),
                contentDescription = "${item.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(12.dp),
                text = item.name
            )
        }
    }
}

@Preview
@Composable
//preview doesn't accept parameters
fun ColoredTextPreview() = ListedItemView(getExampleCharacter(), {})
private fun getExampleCharacter() = ListingScreenViewForm(
    pokemonSingleInfo = PokemonListItemDomainModel(
        name = "name",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
    )
)
