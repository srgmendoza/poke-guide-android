package com.sm.poke_features.listing.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.collections.addAll
import kotlin.text.clear

class ListingScreenViewModel(
    private val locale: Locale,
    private val pagingHandler: ListingScreenPagingHandler,
) : BaseViewModel<ListingScreenViewState>() {

    override val _viewState: MutableStateFlow<ListingScreenViewState> =
        MutableStateFlow(ListingScreenViewState.Initial)

    fun fetchContent() {
        _viewState.value = ListingScreenViewState.Loading

        viewModelScope.launch {
            pagingHandler.getPagedData()
                .map {
                    it.map { poke ->
                        ListingScreenViewForm(
                            pokemonSingleInfo = poke,
                            locale = locale
                        )
                    }
                }
                .cachedIn(viewModelScope)
                .collect {
                    _viewState.value = ListingScreenViewState.Success(
                        form = MutableStateFlow(it)
                    )
                }
        }
    }
}

data class ListingScreenViewForm(
    private val locale: Locale? = null,
    private val pokemonSingleInfo: PokemonListItemDomainModel = PokemonListItemDomainModel()
) {
    val name
        get() = locale?.let {
            pokemonSingleInfo.name.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(locale)
                else
                    it.toString()
            }
        } ?: pokemonSingleInfo.name

    val imageUrl get() = pokemonSingleInfo.imageUrl
}

sealed class ListingScreenViewState(
    val form: Flow<PagingData<ListingScreenViewForm>>,
    state: State<Any>
) :
    ViewState<Any>(state = state) {

    object Initial :
        ListingScreenViewState(form = flowOf(PagingData.empty()), state = State.Initial())

    object Loading :
        ListingScreenViewState(form = flowOf(PagingData.empty()), state = State.Loading())

    class Error(message: String?) :
        ListingScreenViewState(
            form = flowOf(PagingData.empty()),
            state = State.Error(message = message)
        )

    class Success(form: Flow<PagingData<ListingScreenViewForm>>) :
        ListingScreenViewState(
            form = form,
            state = State.Success()
        )
}