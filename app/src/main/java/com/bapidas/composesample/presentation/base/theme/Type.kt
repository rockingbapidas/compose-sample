package com.bapidas.composesample.presentation.base.theme

import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bapidas.composesample.R

private val regular = Font(R.font.roboto_slab_regular)
private val medium = Font(R.font.roboto_slab_medium, FontWeight.W500)
private val semi_bold = Font(R.font.roboto_slab_semi_bold, FontWeight.W600)
private val bold = Font(R.font.roboto_slab_bold, FontWeight.W700)

val robotoFamily = FontFamily(
    Font(R.font.roboto_slab_light, FontWeight.Light),
    Font(R.font.roboto_slab_regular, FontWeight.Normal),
    Font(R.font.roboto_slab_medium, FontWeight.Medium),
    Font(R.font.roboto_slab_bold, FontWeight.Bold)
)

private val appFontFamily = FontFamily(
    fonts = listOf(
        regular,
        medium,
        semi_bold,
        bold
    )
)

private val bodyFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.roboto_slab_regular),
        Font(R.font.roboto_slab_bold, FontWeight.Bold)
    )
)

val themeTypography = Typography(
    h4 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = bodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)

val LightThemeColors = lightColors(
    primary = Red700,
    primaryVariant = Red700,
    secondary = Red700,
    secondaryVariant = Red700,
    error = Red900
)

val DarkThemeColors = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Black,
    error = Red900
)
