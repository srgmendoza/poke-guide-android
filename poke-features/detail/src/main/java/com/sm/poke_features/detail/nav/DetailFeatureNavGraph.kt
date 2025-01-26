package com.sm.poke_features.detail.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sm.core.navigation.ModuleRoutes
import com.sm.poke_features.detail.ui.PokemonDetailScreen
import com.sm.poke_features.detail.ui.PokemonDetailScreenViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

fun NavGraphBuilder.addDetailFeatureNavGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = "${ModuleRoutes.MainDetailScreen.label}/{pokeName}",
        route = "${ModuleRoutes.DetailFeature.label}/{pokeName}"
    ) {
        composable(
            route = "${ModuleRoutes.MainDetailScreen.label}/{pokeName}",
            arguments = listOf(navArgument("pokeName") { type = StringType })
        ) { backStackEntry ->
            val pokeName = backStackEntry.arguments?.getString("pokeName")

            if (pokeName != null) {
                val viewModel: PokemonDetailScreenViewModel =
                    koinViewModel<PokemonDetailScreenViewModel>()

                val name = pokeName.toLowerCase(Locale.ROOT)

                PokemonDetailScreen(name, viewModel)
            }
        }
    }
}