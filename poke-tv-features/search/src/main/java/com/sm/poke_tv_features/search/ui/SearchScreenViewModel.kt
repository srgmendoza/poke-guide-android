package com.sm.poke_tv_features.search.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sm.core.commons.BaseViewModel
import com.sm.core.commons.State
import com.sm.core.commons.ViewState
import com.sm.core.navigation.Navigator
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.use_cases.GetPokeSearchByName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    navigator: Navigator,
    private val useCaseByName: GetPokeSearchByName
) : BaseViewModel<SearchScreenViewState>(navigator = navigator) {

    override val _viewState: MutableStateFlow<SearchScreenViewState> =
        MutableStateFlow(SearchScreenViewState.Initial)

    private var searchJob: Job? = null

    fun clearState() {
        _viewState.value = SearchScreenViewState.Initial
    }

    fun searchByName(name: String) {
        if (name.length > 3) {
            Log.d(className, "Will search for $name")
            cancelJob()
            _viewState.value = SearchScreenViewState.Loading
            searchJob = viewModelScope.launch {
                delay(600)
                useCaseByName.execute(name).fold(
                    onSuccess = {
                        Log.d(className, "Result for $name is $it")
                        _viewState.value = SearchScreenViewState.Success(
                            SearchScreenViewForm(
                                pokemonSingleInfo = it
                            )
                        )
                    },
                    onFailure = {
                        Log.d(className, "Error $it looking for $name")
                        _viewState.value =
                            SearchScreenViewState.Error(it.message ?: "Unknown error")
                    }
                )
            }
        } else {
            cancelJob()
            clearState()
        }
    }

    private fun cancelJob() {
        searchJob?.cancel()
    }

}

data class SearchScreenViewForm(
    private val pokemonSingleInfo: PokeDetailDomainModel? = null,
    val searchValue: String = ""
) {
    val pokemonSearchResult get() = pokemonSingleInfo
}

sealed class SearchScreenViewState(
    val form: SearchScreenViewForm = SearchScreenViewForm(),
    state: State<Any>
) :
    ViewState<Any>(state = state) {

    object Initial :
        SearchScreenViewState(state = State.Initial())

    object Loading :
        SearchScreenViewState(state = State.Loading())

    class Error(message: String?) :
        SearchScreenViewState(
            state = State.Error(message = message)
        )

    class Success(form: SearchScreenViewForm) :
        SearchScreenViewState(
            form = form,
            state = State.Success()
        )
}