package com.sm.poke_theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.tv.material3.darkColorScheme as TVDarkColorScheme
import androidx.tv.material3.lightColorScheme as TVLightColorScheme
import androidx.compose.ui.graphics.Color

// This color palette have been made using ChatGPT ☺️

val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFCC00), // Pikachu Yellow
    onPrimary = Color(0xFF000000), // Black for contrast
    primaryContainer = Color(0xFFFFE680), // Light Yellow
    onPrimaryContainer = Color(0xFF333300), // Dark Yellow contrast

    secondary = Color(0xFFFF6F61), // Charmander Red-Orange
    onSecondary = Color(0xFFFFFFFF), // White for contrast
    secondaryContainer = Color(0xFFFFA88C), // Light Red-Orange
    onSecondaryContainer = Color(0xFF4C1D00), // Deep Orange contrast

    tertiary = Color(0xFF00BFFF), // Squirtle Blue
    onTertiary = Color(0xFFFFFFFF), // White for contrast
    tertiaryContainer = Color(0xFF99E2FF), // Light Blue
    onTertiaryContainer = Color(0xFF00264D), // Deep Blue contrast

    background = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF202124), // Deep Gray
    surface = Color(0xFFF8F9FA), // Light Gray
    onSurface = Color(0xFF202124), // Deep Gray

    error = Color(0xFFB00020), // Red
    onError = Color(0xFFFFFFFF) // White for contrast
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFCC00), // Pikachu Yellow
    onPrimary = Color(0xFF333300), // Dark Yellow contrast
    primaryContainer = Color(0xFF665700), // Deep Yellow
    onPrimaryContainer = Color(0xFFFFE680), // Light Yellow

    secondary = Color(0xFFFF6F61), // Charmander Red-Orange
    onSecondary = Color(0xFF4C1D00), // Deep Orange contrast
    secondaryContainer = Color(0xFF7F2F21), // Deep Red-Orange
    onSecondaryContainer = Color(0xFFFFA88C), // Light Red-Orange

    tertiary = Color(0xFF00BFFF), // Squirtle Blue
    onTertiary = Color(0xFF00264D), // Deep Blue contrast
    tertiaryContainer = Color(0xFF003F66), // Deep Blue
    onTertiaryContainer = Color(0xFF99E2FF), // Light Blue

    background = Color(0xFF202124), // Deep Gray
    onBackground = Color(0xFFE8EAED), // Light Gray
    surface = Color(0xFF303134), // Dark Gray
    onSurface = Color(0xFFE8EAED), // Light Gray

    error = Color(0xFFCF6679), // Light Red
    onError = Color(0xFFB00020) // Dark Red
)

// TV

val TVLightColorScheme = TVLightColorScheme(
    primary = Color(0xFFFFCC00), // Pikachu Yellow
    onPrimary = Color(0xFF000000), // Black for contrast
    primaryContainer = Color(0xFFFFE680), // Light Yellow
    onPrimaryContainer = Color(0xFF333300), // Dark Yellow contrast

    secondary = Color(0xFFFF6F61), // Charmander Red-Orange
    onSecondary = Color(0xFFFFFFFF), // White for contrast
    secondaryContainer = Color(0xFFFFA88C), // Light Red-Orange
    onSecondaryContainer = Color(0xFF4C1D00), // Deep Orange contrast

    tertiary = Color(0xFF00BFFF), // Squirtle Blue
    onTertiary = Color(0xFFFFFFFF), // White for contrast
    tertiaryContainer = Color(0xFF99E2FF), // Light Blue
    onTertiaryContainer = Color(0xFF00264D), // Deep Blue contrast

    background = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF202124), // Deep Gray
    surface = Color(0xFFF8F9FA), // Light Gray
    onSurface = Color(0xFF202124), // Deep Gray

    error = Color(0xFFB00020), // Red
    onError = Color(0xFFFFFFFF) // White for contrast
)

val TVDarkColorScheme = TVDarkColorScheme(
    primary = Color(0xFFFFCC00), // Pikachu Yellow
    onPrimary = Color(0xFF333300), // Dark Yellow contrast
    primaryContainer = Color(0xFF665700), // Deep Yellow
    onPrimaryContainer = Color(0xFFFFE680), // Light Yellow

    secondary = Color(0xFFFF6F61), // Charmander Red-Orange
    onSecondary = Color(0xFF4C1D00), // Deep Orange contrast
    secondaryContainer = Color(0xFF7F2F21), // Deep Red-Orange
    onSecondaryContainer = Color(0xFFFFA88C), // Light Red-Orange

    tertiary = Color(0xFF00BFFF), // Squirtle Blue
    onTertiary = Color(0xFF00264D), // Deep Blue contrast
    tertiaryContainer = Color(0xFF003F66), // Deep Blue
    onTertiaryContainer = Color(0xFF99E2FF), // Light Blue

    background = Color(0xFF202124), // Deep Gray
    onBackground = Color(0xFFE8EAED), // Light Gray
    surface = Color(0xFF303134), // Dark Gray
    onSurface = Color(0xFFE8EAED), // Light Gray

    error = Color(0xFFCF6679), // Light Red
    onError = Color(0xFFB00020) // Dark Red
)