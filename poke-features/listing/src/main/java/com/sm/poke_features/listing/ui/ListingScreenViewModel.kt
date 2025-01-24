package com.sm.poke_features.listing.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sm.core.ui.commons.BaseViewModel
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import kotlinx.coroutines.launch

class ListingScreenViewModel(
    private val useCase: GetPokeListUseCase
) : BaseViewModel<ListingScreenViewModel>() {

    init {
        viewModelScope.launch {
            val result = useCase.execute()
            Log.d("ViewModel","The result is: $result")
        }
    }
}