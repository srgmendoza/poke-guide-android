package com.sm.poke_tv_features.search.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.NavTVDestination
import com.sm.core.tv_components.NavigationDrawerContainer
import com.sm.poke_tv_features.search.ui.SearchScreen

fun NavGraphBuilder.addSearchTvFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavTVDestination.SearchScreen.label,
        route = NavTVDestination.SearchFeature.label
    ) {
        composable(NavTVDestination.SearchScreen.label) {
            NavigationDrawerContainer(
                currentRoute = NavTVDestination.SearchScreen.label,
            )
            {
                SearchScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}