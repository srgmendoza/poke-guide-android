package com.sm.pokeguide.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sm.core.navigation.NavDestination
import com.sm.core.navigation.Navigator
import com.sm.poke_features.listing.nav.addListingFeatureNavGraph
import com.sm.poke_features.search.nav.addSearchFeatureNavGraph
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator,
    modifier: Modifier
) {
    LaunchedEffect("key") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.label) {
                popUpTo(it.label)
            }
        }.launchIn(this)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavDestination.ListingFeature.label
    ) {
        addListingFeatureNavGraph { navController.popBackStack() }
        addSearchFeatureNavGraph { navController.popBackStack() }
    }
}