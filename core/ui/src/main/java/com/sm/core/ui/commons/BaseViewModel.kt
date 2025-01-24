package com.sm.core.ui.commons

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewModel> : ViewModel(), NavigableViewModel {

    private val className: String? = this::class.simpleName

    init {
        Log.d("ViewModel", "$className Initialising...")
    }

    override fun onCleared() {
        Log.d("ViewModel", "$className Cleared...")
        super.onCleared()
    }
}