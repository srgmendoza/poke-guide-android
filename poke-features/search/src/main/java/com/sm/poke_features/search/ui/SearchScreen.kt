package com.sm.poke_features.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sm.core.navigation.NavMobileDestination
import com.sm.core.ui.components.PokeItemView
import com.sm.core.ui.components.PokeLoaderView
import com.sm.poke_features.search.ui.components.SearchBoxView

@Composable
fun SearchScreen(initialText: String, viewModel: SearchScreenViewModel) {
    val viewState = viewModel.viewState.collectAsState()

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initialText,
                selection = TextRange(initialText.length)
            )
        )
    }

    val onSearchAction: (TextFieldValue) -> Unit = {
        textFieldValue = it
        viewModel.searchByName(it.text)
    }

    when (viewState.value) {

        is SearchScreenViewState.Initial -> {
            SearchScreenContent(
                searchValue = textFieldValue,
                onSearchName = onSearchAction,
                onItemClicked = {}
            )
        }

        else -> {
            SearchScreenContent(
                searchValue = textFieldValue,
                form = viewState.value.form,
                isLoading = viewState.value.isLoading,
                isError = viewState.value.isError,
                onSearchName = onSearchAction,
                onClearSearch = {
                    viewModel.clearState()
                },
                onItemClicked = {
                    viewModel.goTo(NavMobileDestination.DetailFeature.PokeDetailScreen(it))
                }
            )
        }
    }
}

@Composable
private fun SearchScreenContent(
    searchValue: TextFieldValue,
    form: SearchScreenViewForm? = null,
    isLoading: Boolean = false,
    isError: Boolean = false,
    onSearchName: (TextFieldValue) -> Unit,
    onClearSearch: () -> Unit = {},
    onItemClicked: (String) -> Unit
) {

    Column {
        SearchBoxView(
            value = searchValue,
            onValueChange = {
                onSearchName(it)
            },
            onClearSearch = onClearSearch,
            onSearch = {}
        )

        Box {
            when {
                isLoading -> {
                    PokeLoaderView()
                }

                isError -> {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "No result for your search with ${searchValue.text} keyword."
                    )
                }

                else -> {
                    form?.let {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(150.dp),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            item(span = { GridItemSpan(maxLineSpan) }) {
                                PokeItemView(
                                    pokeName = it.pokemonSingleInfo?.name ?: "",
                                    pokeImageUrl = it.pokemonSingleInfo?.imageUrl,
                                    onClick = onItemClicked
                                )
                            }

                            it.pokemonSingleInfo?.let { it1 ->
                                if (it1.relatedPokemons.isNotEmpty()) {
                                    item(span = { GridItemSpan(maxLineSpan) }) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text("Pokemons related to your search...")
                                        }
                                    }
                                    items(it1.relatedPokemons) { item ->
                                        PokeItemView(
                                            pokeName = item.name,
                                            pokeImageUrl = item.imageUrl,
                                            onClick = onItemClicked
                                        )
                                    }
                                }
                            }
                        }
                    } ?: run {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Please type your Poke Query, with at least 4 characters."
                        )
                    }
                }
            }
        }
    }
}