package com.sm.pokeguide.app_tv.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.tv.material3.Text
import com.sm.core.navigation.Navigator
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
        startDestination = "mainTvFeatureDestination"
    ) {
        navigation(
            startDestination = "mainTvDestination",
            route = "mainTvFeatureDestination"
        ) {
            composable(
                route = "mainTvDestination"
            ) {
                TestScreen()
            }
        }
    }
}

@Composable
private fun TestScreen() {
    Text(text = "Test")
}