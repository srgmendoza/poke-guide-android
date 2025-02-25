package com.sm.poke_theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.tv.material3.MaterialTheme

@Composable
fun PokeTvTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    /*CompositionLocalProvider {
        // TODO. Set here any composition local config
    }*/

    MaterialTheme(
        colorScheme = if (isDarkTheme) TVDarkColorScheme else TVLightColorScheme,
        typography = TVTypography,
        content = content
    )
}