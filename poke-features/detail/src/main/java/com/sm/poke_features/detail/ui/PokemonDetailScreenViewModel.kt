package com.sm.poke_features.detail.ui

import com.sm.core.navigation.Navigator
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_domain.models.PokeDetailDomainModel
import kotlinx.coroutines.flow.MutableStateFlow

class PokemonDetailScreenViewModel(private val navigator: Navigator) :
    BaseViewModel<PokemonDetailScreenViewState>(navigator) {
    override val _viewState: MutableStateFlow<PokemonDetailScreenViewState> =
        MutableStateFlow(PokemonDetailScreenViewState.Initial)

    fun getPokemonDetail(name: String) {
        _viewState.value = PokemonDetailScreenViewState.Loading
    }
}

data class PokemonDetailScreenViewForm(
    val domainModel: PokeDetailDomainModel? = null
)

sealed class PokemonDetailScreenViewState(
    val form: PokemonDetailScreenViewForm,
    state: State<Any>
) : ViewState<Any>(state = state) {
    object Initial :
        PokemonDetailScreenViewState(form = PokemonDetailScreenViewForm(), state = State.Initial())

    object Loading :
        PokemonDetailScreenViewState(form = PokemonDetailScreenViewForm(), state = State.Loading())

    class Error(message: String?) :
        PokemonDetailScreenViewState(
            form = PokemonDetailScreenViewForm(),
            state = State.Error(message = message)
        )

    class Success(form: PokemonDetailScreenViewForm) :
        PokemonDetailScreenViewState(
            form = form,
            state = State.Success()
        )
}