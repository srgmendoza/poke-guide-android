package com.sm.poke_tv_features.listing.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.poke_tv_features.listing.ui.MainHomeScreen
import com.sm.poke_tv_features.listing.ui.MainHomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.addHomeTvFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = "mainTvDestination",
        route = "mainTvFeatureDestination"
    ) {
        composable(
            route = "mainTvDestination"
        ) {
            val viewModel = koinViewModel<MainHomeScreenViewModel>()
            MainHomeScreen(viewModel = viewModel)
        }
    }
}