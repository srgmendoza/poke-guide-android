package com.sm.core.commons

import android.util.Log
import com.sm.core.navigation.NavDestination
import com.sm.core.navigation.NavMobileDestination

interface NavigableViewModel {
    fun goTo(destination: NavDestination) {
        Log.d("navigation", "navigate to ${destination.label}")
    }
}