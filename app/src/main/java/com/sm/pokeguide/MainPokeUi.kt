package com.sm.pokeguide

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sm.poke_theme.PokeTheme
import com.sm.pokeguide.nav.MainNavigationUi

@Composable
fun MainPokeUi() {
    val navController = rememberNavController()

    PokeTheme {
        Scaffold { innerPadding ->
            MainNavigationUi(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}