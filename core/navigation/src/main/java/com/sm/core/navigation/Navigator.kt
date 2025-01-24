package com.sm.core.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface Navigator {
    val sharedFlow: SharedFlow<NavDestination>
    fun navigateTo(
        destination: NavDestination
    )
}

class NavigatorImpl : Navigator {
    private val _sharedFlow = MutableSharedFlow<NavDestination>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val sharedFlow = _sharedFlow.asSharedFlow()

    override fun navigateTo(destination: NavDestination) {
        _sharedFlow.tryEmit(destination)
    }
}