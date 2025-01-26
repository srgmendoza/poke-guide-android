package com.sm.poke_features.search.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sm.core.ui.commons.BaseViewModel
import com.sm.core.ui.commons.State
import com.sm.core.ui.commons.ViewState
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.use_cases.GetPokeSearchByName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class SearchScreenViewModel(
    private val useCaseByName: GetPokeSearchByName
) :
    BaseViewModel<SearchScreenViewState>() {

    override val _viewState: MutableStateFlow<SearchScreenViewState> =
        MutableStateFlow(SearchScreenViewState.Initial)

    private var searchJob: Job? = null

    fun searchByName(name: String) {
        if (name.length > 4) {
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
                    }
                )
            }
        }
    }

    private fun cancelJob() {
        searchJob?.cancel()
    }
}

data class SearchScreenViewForm(
    private val locale: Locale? = null,
    val pokemonSingleInfo: PokeDetailDomainModel? = null
) {

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