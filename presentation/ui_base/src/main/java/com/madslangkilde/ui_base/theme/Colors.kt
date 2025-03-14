package com.madslangkilde.ui_base.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Blue Shades
val Blue100 = Color(0xffB3E5FC)
val Blue200 = Color(0xff81D4FA)
val Blue300 = Color(0xff4FC3F7)
val Blue400 = Color(0xff29B6F6)
val Blue500 = Color(0xff03A9F4)
val Blue600 = Color(0xff039BE5)
val Blue700 = Color(0xff0288D1)
val Blue800 = Color(0xff0277BD)
val Blue900 = Color(0xff01579B)

// Green Shades
val Green100 = Color(0xffC8E6C9)
val Green200 = Color(0xffA5D6A7)
val Green300 = Color(0xff81C784)
val Green400 = Color(0xff66BB6A)
val Green500 = Color(0xff4CAF50)
val Green600 = Color(0xff43A047)
val Green700 = Color(0xff388E3C)
val Green800 = Color(0xff2E7D32)
val Green900 = Color(0xff1B5E20)

// Red Shades
val Red100 = Color(0xffFFCDD2)
val Red200 = Color(0xffEF9A9A)
val Red300 = Color(0xffE57373)
val Red400 = Color(0xffEF5350)
val Red500 = Color(0xffF44336)
val Red600 = Color(0xffE53935)
val Red700 = Color(0xffD32F2F)
val Red800 = Color(0xffC62828)
val Red900 = Color(0xffB71C1C)

// Yellow Shades
val Yellow100 = Color(0xffFFF9C4)
val Yellow200 = Color(0xffFFF59D)
val Yellow300 = Color(0xffFFF176)
val Yellow400 = Color(0xffFFEE58)
val Yellow500 = Color(0xffFFEB3B)
val Yellow600 = Color(0xffFDD835)
val Yellow700 = Color(0xffFBC02D)
val Yellow800 = Color(0xffF9A825)
val Yellow900 = Color(0xffF57F17)

// Grey Shades
val Grey100 = Color(0xffF5F5F5)
val Grey200 = Color(0xffEEEEEE)
val Grey300 = Color(0xffE0E0E0)
val Grey400 = Color(0xffBDBDBD)
val Grey500 = Color(0xff9E9E9E)
val Grey600 = Color(0xff757575)
val Grey700 = Color(0xff616161)
val Grey800 = Color(0xff424242)
val Grey900 = Color(0xff212121)

// Other
val White = Color(0xffFFFFFF)
val Black = Color(0xff000000)
val ThemeColor = Color(0xff400fa5)
val WhiteTransparent = Color(0x33ffffff)

val LightThemeColors = lightColorScheme(
    primary = Blue700,
    onPrimary = Color.White,
    primaryContainer = Blue100,
    onPrimaryContainer = Blue900,
    secondary = Blue700,
    onSecondary = Color.White,
    secondaryContainer = Blue100,
    onSecondaryContainer = Blue900,
    tertiary = Blue800,
    onTertiary = Color.White,
    tertiaryContainer = Blue100,
    onTertiaryContainer = Blue900,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Blue100,
    onSurfaceVariant = Blue900,
    error = Blue800,
    onError = Color.White,
    errorContainer = Blue100,
    onErrorContainer = Blue900,
    outline = Blue900,
    outlineVariant = Blue900,
    scrim = Color.Black,
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    inversePrimary = Blue100,
    surfaceTint = Blue700
)

val DarkThemeColors = darkColorScheme(
    primary = Blue300,
    onPrimary = Color.Black,
    primaryContainer = Blue900,
    onPrimaryContainer = Blue100,
    secondary = Blue300,
    onSecondary = Color.Black,
    secondaryContainer = Blue900,
    onSecondaryContainer = Blue100,
    tertiary = Blue200,
    onTertiary = Color.Black,
    tertiaryContainer = Blue900,
    onTertiaryContainer = Blue100,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    surfaceVariant = Blue900,
    onSurfaceVariant = Blue100,
    error = Blue200,
    onError = Color.Black,
    errorContainer = Blue900,
    onErrorContainer = Blue100,
    outline = Blue100,
    outlineVariant = Blue100,
    scrim = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
    inversePrimary = Blue900,
    surfaceTint = Blue300
)