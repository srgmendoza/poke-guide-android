package com.sm.pokeguide.app_tv.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Surface
import com.sm.core.navigation.Navigator
import com.sm.poke_theme.PokeTvTheme
import com.sm.pokeguide.app_tv.nav.TVNavigationComponent
import org.koin.androidx.compose.get

@Composable
fun MainPokeTvUI() {
    val navController = rememberNavController()
    val navigator = get<Navigator>()

    PokeTvTheme(isDarkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RectangleShape
        ) {
            TVNavigationComponent(
                navController = navController,
                navigator = navigator,
                modifier = Modifier
            )
        }
    }
}
