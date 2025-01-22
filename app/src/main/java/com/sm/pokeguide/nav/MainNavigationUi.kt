package com.sm.pokeguide.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sm.core.navigation.CoreNavigation
import com.sm.core.navigation.models.NavigationConfig

@Composable
fun MainNavigationUi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navConfig = remember { getNavConfig() }

    NavHost(
        navController = navController,
        startDestination = navConfig.startDestinationRoute,
        modifier = modifier
    ) {
        navConfig.featuresConfig.forEach { destination ->
            register(
                featureNavigation = destination.navInstance,
                navController = navController,
                modifier = modifier
            )
        }
    }
}

fun NavGraphBuilder.register(
    featureNavigation: CoreNavigation,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureNavigation.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}

private fun getNavConfig(): NavigationConfig {

    val config = NavigationConfig(
        startDestinationRoute = "", //TO be Determined
        featuresConfig = listOf() //TO be determined
    )

    return config
}