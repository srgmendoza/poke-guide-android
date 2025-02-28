package com.sm.poke_tv_features.detail.ui

import androidx.lifecycle.viewModelScope
import com.sm.core.commons.BaseViewModel
import com.sm.core.commons.State
import com.sm.core.commons.ViewState
import com.sm.core.navigation.Navigator
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.use_cases.GetPokeDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    navigator: Navigator,
    private val useCase: GetPokeDetailUseCase,
) : BaseViewModel<DetailScreenViewState>(navigator) {

    override val _viewState: MutableStateFlow<DetailScreenViewState> =
        MutableStateFlow(DetailScreenViewState.Initial)

    fun getPokemonDetail(pokemonId: String) {
        _viewState.value = DetailScreenViewState.Loading
        viewModelScope.launch {
            useCase.execute(pokemonId).fold(
                onSuccess = {
                    _viewState.value = DetailScreenViewState.Success(
                        form = DetailScreenViewForm(
                            domainModel = it
                        )
                    )
                },
                onFailure = {
                    _viewState.value = DetailScreenViewState.Error(it.message)
                }
            )
        }
    }

}

data class DetailScreenViewForm(
    private val domainModel: PokeDetailDomainModel? = null
) {
    val id get() = "ID: #${domainModel?.id ?: ""}"

    val name get() = (domainModel?.name ?: "").capitalize()

    val imageUrl get() = domainModel?.imageUrl

    val weight get() = "${domainModel?.weight ?: 0} kg"

    val height get() = "${domainModel?.height ?: 0} m"
}

sealed class DetailScreenViewState(
    val form: DetailScreenViewForm,
    state: State<Any>
) : ViewState<Any>(state = state) {
    object Initial :
        DetailScreenViewState(form = DetailScreenViewForm(), state = State.Initial())

    object Loading :
        DetailScreenViewState(form = DetailScreenViewForm(), state = State.Loading())

    class Error(message: String?) :
        DetailScreenViewState(
            form = DetailScreenViewForm(),
            state = State.Error(message = message)
        )

    class Success(form: DetailScreenViewForm) :
        DetailScreenViewState(
            form = form,
            state = State.Success()
        )
}