package com.sm.core.commons

import android.util.Log
import com.sm.core.navigation.NavMobileDestination

interface NavigableViewModel {
    fun goTo(destination: NavMobileDestination) {
        Log.d("navigation", "navigate to ${destination.label}")
    }
}