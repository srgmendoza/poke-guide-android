package com.sm.poke_features.search.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.NavDestination

fun NavGraphBuilder.addSearchFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavDestination.ListinMainScreen.label,
        route = NavDestination.ListingFeature.label
    ) {
        composable(
            route = NavDestination.ListinMainScreen.label
        ) {
            // TODO, add composable
        }
    }
}