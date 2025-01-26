package com.sm.poke_features.listing.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.sm.core.navigation.NavDestination
import com.sm.core.ui.components.PokeItemView
import com.sm.core.ui.components.PokeLoaderView
import com.sm.core.ui.components.PokeVerticalGridView
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_features.search.ui.components.SearchBoxView
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
            ListingScreenContent(
                pagedForm = viewState.value.form,
                onPokeSelected = { pokemonName ->
                    viewModel.goTo(NavDestination.DetailFeature.PokeDetailScreen(pokemonName))
                },
                onSearchRequested = {
                    viewModel.goTo(NavDestination.SearchFeature.SearchScreenWithText(it))
                }
            )
        }
    }
}

@Composable
private fun ListingScreenContent(
    pagedForm: Flow<PagingData<ListingScreenViewForm>>,
    onPokeSelected: (String) -> Unit,
    onSearchRequested: (String) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange.Zero
            )
        )
    }

    Column {
        SearchBoxView(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchRequested(it.text)
            },
            onSearch = {}
        )
        PagedGridView(
            pagedPokemons = pagedForm,
            onClick = onPokeSelected
        )
    }
}

@Composable
private fun PagedGridView(
    pagedPokemons: Flow<PagingData<ListingScreenViewForm>>,
    onClick: (String) -> Unit
) {
    val lazyPagingItems = pagedPokemons.collectAsLazyPagingItems()
    val gridState = rememberLazyGridState()

    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            PokeLoaderView(modifier = Modifier.fillMaxWidth())
        }

        is LoadState.Error -> {
            // item { onErrorItem("Error Refreshing Screen") }
        }

        else -> {
            PokeVerticalGridView<ListingScreenViewForm>(
                gridState = gridState,
                items = lazyPagingItems,
                itemView = { item ->
                    PokeItemView(
                        pokeName = item.name,
                        pokeImageUrl = item.imageUrl,
                        onClick = onClick
                    )
                }
            ) {
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

@Preview
@Composable
//preview doesn't accept parameters
fun ColoredTextPreview() =
    PokeItemView(
        pokeName = getExampleCharacter().name,
        pokeImageUrl = getExampleCharacter().imageUrl ?: "",
        onClick = {}
    )

private fun getExampleCharacter() = ListingScreenViewForm(
    pokemonSingleInfo = PokemonListItemDomainModel(
        name = "name",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
    )
)
