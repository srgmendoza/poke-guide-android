package com.sm.poke_features.listing.nav


import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.NavDestination
import com.sm.poke_features.listing.ui.ListingScreen
import com.sm.poke_features.listing.ui.ListingScreenViewModel
import org.koin.androidx.compose.defaultExtras
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.rememberCurrentKoinScope

fun NavGraphBuilder.addListingFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavDestination.ListinMainScreen.label,
        route = NavDestination.ListingFeature.label
    ) {
        composable(
            route = NavDestination.ListinMainScreen.label
        ) {
            val viewModel: ListingScreenViewModel = koinViewModel<ListingScreenViewModel>()

            ListingScreen(viewModel)
        }
    }
}