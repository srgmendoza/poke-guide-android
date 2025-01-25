package com.sm.core.ui.commons

import android.util.Log
import com.sm.core.navigation.NavDestination

interface NavigableViewModel {
    fun goTo(destination: NavDestination){
        Log.d("navigation", "navigate to ${destination.label}")
    }
}