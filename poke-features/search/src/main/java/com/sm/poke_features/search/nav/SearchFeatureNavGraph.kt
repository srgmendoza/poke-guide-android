package com.sm.poke_features.search.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sm.core.navigation.ModuleRoutes
import com.sm.poke_features.search.ui.SearchScreen
import com.sm.poke_features.search.ui.SearchScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.addSearchFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = "${ModuleRoutes.SearchScreen.label}/{initialText}",
        route = "${ModuleRoutes.SearchFeature.label}/{initialText}"
    ) {
        composable(
            route = "${ModuleRoutes.SearchScreen.label}/{initialText}",
            arguments = listOf(navArgument("initialText") { type = StringType })
        ) { backStackEntry ->
            val searchText = backStackEntry.arguments?.getString("initialText")
            if (searchText != null) {
                val viewModel: SearchScreenViewModel = koinViewModel<SearchScreenViewModel>()

                SearchScreen(searchText, viewModel)
            }
        }
    }
}