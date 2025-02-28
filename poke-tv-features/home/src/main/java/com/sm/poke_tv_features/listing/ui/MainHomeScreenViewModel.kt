package com.sm.poke_tv_features.listing.ui

import androidx.lifecycle.viewModelScope
import com.sm.core.navigation.Navigator
import com.sm.core.commons.BaseViewModel
import com.sm.core.commons.State
import com.sm.core.commons.ViewState
import com.sm.poke_domain.models.PokeTypesWithChildrenDomainModel
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_domain.use_cases.GetOutstandingPokemonListUseCase
import com.sm.poke_domain.use_cases.GetPokeTypesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainHomeScreenViewModel(
    navigator: Navigator,
    private val outstandingUseCase: GetOutstandingPokemonListUseCase,
    private val ribbonsUseCase: GetPokeTypesUseCase
) :
    BaseViewModel<MainHomeScreenViewState>(navigator = navigator) {
    override val _viewState: MutableStateFlow<MainHomeScreenViewState> =
        MutableStateFlow(MainHomeScreenViewState.Initial)

    fun fetchContent() {
        _viewState.value = MainHomeScreenViewState.Loading
        viewModelScope.launch {
            outstandingUseCase.execute().fold(
                onSuccess = {
                    _viewState.value = MainHomeScreenViewState.Success(
                        form = viewState.value.form.copy(
                            outstandingPokemons = it
                        )
                    )
                },
                onFailure = {
                    _viewState.value = MainHomeScreenViewState.Error(it.message)
                }
            )

            ribbonsUseCase.execute().fold(
                onSuccess = {
                    _viewState.value = MainHomeScreenViewState.Success(
                        form = viewState.value.form.copy(
                            typePokeRibbons = it
                        )
                    )
                },
                onFailure = {
                    _viewState.value = MainHomeScreenViewState.Error(it.message)
                }
            )
        }
    }
}

data class MainHomeScreenViewForm(
    private val outstandingPokemons: List<PokemonListItemDomainModel> = emptyList(),
    private val typePokeRibbons: List<PokeTypesWithChildrenDomainModel> = emptyList()
) {
    val pokemons get() = outstandingPokemons
    val ribbons get() = typePokeRibbons
}

sealed class MainHomeScreenViewState(
    val form: MainHomeScreenViewForm,
    state: State<Any>
) : ViewState<Any>(state = state) {
    object Initial :
        MainHomeScreenViewState(form = MainHomeScreenViewForm(), state = State.Initial())

    object Loading :
        MainHomeScreenViewState(form = MainHomeScreenViewForm(), state = State.Loading())

    class Error(message: String?) : MainHomeScreenViewState(
        form = MainHomeScreenViewForm(),
        state = State.Error(message = message)
    )

    class Success(form: MainHomeScreenViewForm) :
        MainHomeScreenViewState(form = form, state = State.Success())
}
