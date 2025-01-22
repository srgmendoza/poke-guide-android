package com.sm.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.getArgumentByKey(key: String): String? {
    return currentBackStackEntry?.arguments?.getString(key)
}