package com.sm.core.ui.commons

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sm.core.navigation.NavDestination
import com.sm.core.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<STATE>(private val navigator: Navigator) :
    ViewModel(), NavigableViewModel {

    protected val className: String? = this::class.simpleName

    init {
        Log.d("ViewModel", "$className Initialising...")
    }

    override fun onCleared() {
        Log.d("ViewModel", "$className Cleared...")
        super.onCleared()
    }

    protected abstract val _viewState: MutableStateFlow<STATE>

    val viewState: StateFlow<STATE> get() = _viewState

    override fun goTo(destination: NavDestination) {
        super.goTo(destination)
        navigator.navigateTo(destination)
    }
}