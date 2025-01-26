package com.sm.poke_features.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sm.core.ui.components.PokeItemView
import com.sm.core.ui.components.PokeLoaderView
import com.sm.poke_features.search.ui.components.SearchBoxView

@Composable
fun SearchScreen(initialText: String, viewModel: SearchScreenViewModel) {
    val viewState = viewModel.viewState.collectAsState()

    when (viewState.value) {

        is SearchScreenViewState.Initial -> {
            SearchScreenContent(initialText, null, false) {
                viewModel.searchByName(it)
            }
        }

        else -> {
            SearchScreenContent(initialText, viewState.value.form, viewState.value.isLoading) {
                viewModel.searchByName(it)
            }
        }
    }
}

@Composable
private fun SearchScreenContent(
    initialText: String,
    form: SearchScreenViewForm?,
    isLoading: Boolean,
    onSearchName: (String) -> Unit
) {
    var searchText by remember { mutableStateOf(initialText) }
    Column {
        SearchBoxView(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchName(searchText)
            },
            onSearch = {}
        )

        Box {
            if (isLoading) {
                PokeLoaderView()
            } else {
                form?.let {
                    PokeItemView(
                        pokeName = it.pokemonSingleInfo?.name ?: "",
                        pokeImageUrl = it.pokemonSingleInfo?.imageUrl,
                        onClick = {}
                    )
                } ?: run {
                    Text("No result for your search with $searchText")
                }
            }
        }

        val gridState = rememberLazyGridState()

        // Add grid here with related pokemons

    }
}