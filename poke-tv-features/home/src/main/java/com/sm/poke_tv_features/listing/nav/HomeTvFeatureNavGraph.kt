package com.sm.poke_tv_features.listing.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.tv_components.NavigationDrawerContainer
import com.sm.poke_tv_features.listing.ui.MainHomeScreen

fun NavGraphBuilder.addHomeTvFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = "mainTvDestination",
        route = "mainTvFeatureDestination"
    ) {
        composable(
            route = "mainTvDestination"
        ) {
            NavigationDrawerContainer(
                currentRoute = "mainTvDestination",
            ) {
                MainHomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}