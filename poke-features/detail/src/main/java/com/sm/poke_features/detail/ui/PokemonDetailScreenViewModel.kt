package com.sm.poke_features.detail.ui

import androidx.lifecycle.viewModelScope
import com.sm.core.navigation.Navigator
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.use_cases.GetPokeDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonDetailScreenViewModel(
    private val useCase: GetPokeDetailUseCase,
    navigator: Navigator
) :
    BaseViewModel<PokemonDetailScreenViewState>(navigator) {
    override val _viewState: MutableStateFlow<PokemonDetailScreenViewState> =
        MutableStateFlow(PokemonDetailScreenViewState.Initial)

    fun getPokemonDetail(name: String) {
        _viewState.value = PokemonDetailScreenViewState.Loading
        viewModelScope.launch {
            useCase.execute(name).fold(
                onSuccess = {
                    _viewState.value = PokemonDetailScreenViewState.Success(
                        form = _viewState.value.form.copy(
                            domainModel = it
                        )
                    )
                },
                onFailure = {
                    _viewState.value = PokemonDetailScreenViewState.Error(message = it.message)
                }
            )
        }
    }
}

data class PokemonDetailScreenViewForm(
    private val domainModel: PokeDetailDomainModel? = null
) {
    val id get() = "ID: #${domainModel?.id ?: ""}"

    val name get() = (domainModel?.name ?: "").capitalize()

    val imageUrl get() = domainModel?.imageUrl

    val weight get() = "${domainModel?.weight ?: 0} kg"

    val height get() = "${domainModel?.height ?: 0} m"
}

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