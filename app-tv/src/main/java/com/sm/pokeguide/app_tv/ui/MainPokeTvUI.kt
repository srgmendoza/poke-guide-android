package com.sm.pokeguide.app_tv.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sm.core.navigation.Navigator
import com.sm.poke_theme.PokeTheme
import com.sm.pokeguide.app_tv.nav.TVNavigationComponent
import org.koin.androidx.compose.get

@Composable
fun MainPokeTvUI() {
    val navController = rememberNavController()
    val navigator = get<Navigator>()

    PokeTheme(isDarkTheme = true) {
        TVNavigationComponent(
            navController = navController,
            navigator = navigator,
            modifier = Modifier
        )
    }
}
