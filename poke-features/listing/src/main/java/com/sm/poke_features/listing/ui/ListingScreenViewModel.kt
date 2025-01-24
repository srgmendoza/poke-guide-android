package com.sm.poke_features.listing.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListingScreenViewModel(
    private val useCase: GetPokeListUseCase
) : BaseViewModel<ListingScreenViewState>() {

    override val _viewState: MutableStateFlow<ListingScreenViewState> =
        MutableStateFlow(ListingScreenViewState.Initial)

    fun fetchContent() {
        _viewState.value = ListingScreenViewState.Loading
        viewModelScope.launch {
            useCase.execute().fold(
                onSuccess = {
                    _viewState.value = ListingScreenViewState.Success(
                        form = viewState.value.form.copy(
                            domainModel = it
                        )
                    )
                },
                onFailure = {
                    _viewState.value = ListingScreenViewState.Error(
                        message = it.message
                    )
                }
            )
        }
    }
}

data class ListingScreenViewForm(
    val domainModel: List<PokemonListItemDomainModel> = listOf()
) {

}

sealed class ListingScreenViewState(val form: ListingScreenViewForm, state: State<Any>) :
    ViewState<Any>(state = state) {

    object Initial : ListingScreenViewState(form = ListingScreenViewForm(), state = State.Initial())

    object Loading : ListingScreenViewState(form = ListingScreenViewForm(), state = State.Loading())

    class Error(message: String?) :
        ListingScreenViewState(
            form = ListingScreenViewForm(),
            state = State.Error(message = message)
        )

    class Success(form: ListingScreenViewForm) :
        ListingScreenViewState(
            form = form,
            state = State.Success()
        )
}