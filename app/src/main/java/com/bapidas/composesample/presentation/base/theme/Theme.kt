package com.bapidas.composesample.presentation.base.theme

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val LightThemeColors = lightColorPalette(
    primary = Red700,
    primaryVariant = Red700,
    onPrimary = Color.White,

    secondary = Red700,
    secondaryVariant = Red700,
    onSecondary = Red700,

    error = Red900
)

private val DarkThemeColors = darkColorPalette(
    primary = Color.Black,
    primaryVariant = Color.Black,
    onPrimary = Color.Black,

    secondary = Color.Black,
    onSecondary = Color.Black,

    error = Red900
)

@Composable
fun NewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = themeTypography,
        shapes = shapes,
        content = content
    )
}