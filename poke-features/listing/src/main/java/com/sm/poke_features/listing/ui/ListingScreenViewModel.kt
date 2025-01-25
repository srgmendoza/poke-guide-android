package com.sm.poke_features.listing.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListingScreenViewModel(
    private val pagingHandler: ListingScreenPagingHandler
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
                            name = poke.name,
                            imageUrl = poke.imageUrl
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
    val name: String,
    val imageUrl: String? = null
) {

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