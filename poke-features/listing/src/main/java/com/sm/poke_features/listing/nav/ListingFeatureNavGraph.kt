package com.sm.poke_features.listing.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.NavMobileDestination
import com.sm.poke_features.listing.ui.ListingScreen
import com.sm.poke_features.listing.ui.ListingScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.addListingFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavMobileDestination.ListingMainScreen.label,
        route = NavMobileDestination.ListingFeature.label
    ) {
        composable(
            route = NavMobileDestination.ListingMainScreen.label
        ) {
            val viewModel: ListingScreenViewModel = koinViewModel<ListingScreenViewModel>()

            ListingScreen(viewModel)
        }
    }
}