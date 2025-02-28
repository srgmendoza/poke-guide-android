package com.sm.pokeguide.app_tv.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sm.core.navigation.NavTVDestination
import com.sm.core.navigation.Navigator
import com.sm.poke_tv_features.detail.nav.addDetailTvFeatureNavGraph
import com.sm.poke_tv_features.listing.nav.addHomeTvFeatureNavGraph
import com.sm.poke_tv_features.search.nav.addSearchTvFeatureNavGraph
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun TVNavigationComponent(
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
        startDestination = NavTVDestination.HomeFeature.label
    ) {
        addHomeTvFeatureNavGraph { }
        addSearchTvFeatureNavGraph { }
        addDetailTvFeatureNavGraph { navController.popBackStack() }
    }
}
