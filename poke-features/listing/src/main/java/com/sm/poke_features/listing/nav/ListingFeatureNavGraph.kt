package com.sm.poke_features.listing.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.NavDestination
import com.sm.poke_features.listing.ui.LoaderView

fun NavGraphBuilder.addListingFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavDestination.ListinMainScreen.label,
        route = NavDestination.ListingFeature.label
    ) {
        composable(
            route = NavDestination.ListinMainScreen.label
        ) {
            LoaderView() //TODO. At the moment just loader
        }
    }
}