package com.sm.poke_tv_features.detail.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sm.core.navigation.TVModuleRoutes
import com.sm.poke_tv_features.detail.ui.DetailScreen

fun NavGraphBuilder.addDetailTvFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = "${TVModuleRoutes.DETAIL_SCREEN.label}/{id}",
        route = "${TVModuleRoutes.DETAIL_FEATURE.label}/{id}"
    ) {
        composable("${TVModuleRoutes.DETAIL_SCREEN.label}/{id}") {backStackEntry ->
            val pokeId = backStackEntry.arguments?.getString("id")
            if (pokeId != null) {
                DetailScreen(
                    modifier = Modifier.fillMaxSize(),
                    pokemonId = pokeId,
                    onBackRequested = popBackStack
                )
            }
        }
    }
}