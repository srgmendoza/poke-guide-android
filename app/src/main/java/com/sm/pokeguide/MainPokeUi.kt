package com.sm.pokeguide

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sm.core.navigation.Navigator
import com.sm.poke_theme.PokeTheme
import com.sm.pokeguide.nav.NavigationComponent
import org.koin.androidx.compose.get

@Composable
fun MainPokeUi() {
    val navController = rememberNavController()
    val navigator = get<Navigator>()

    PokeTheme {
        Scaffold { innerPadding ->
            NavigationComponent(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                navigator = navigator
            )
        }
    }
}