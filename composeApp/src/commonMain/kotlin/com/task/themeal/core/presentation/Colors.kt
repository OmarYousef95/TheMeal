package com.task.themeal.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection

@Stable
data class CustomColor(
    val primary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val onCard: Color = Color.Unspecified,
    val text: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
)



val LightPrimaryColor = Color(0xFF1B8354)
val LightBackgroundColor = Color(0xFFFFFFFF)
val LightOnBackgroundColor = Color(0xFF1C1C1C)
val LightCardColor = Color(0xFFFFFFFF)
val LightTextColor = Color(0xFF161616)
val LightBoarderColor = Color(0xFFD2D6DB)

val DarkPrimaryColor = Color(0xFF1B8354)
val DarkBackgroundColor = Color(0xFF0D121C)
val DarkTabUnselectedColor = Color(0xFFF0F0F0)
val DarkCardColor = Color(0xFF1F2A37)
val DarkTabSelectedColor = Color(0xFF90CAF9)
val DarkTextColor = Color(0xFFFFFFFF)
val DarkBoarderColor = Color(0xFF6C737F)


val LightCustomColorsPalette = CustomColor(
    primary = LightPrimaryColor,
    background = LightBackgroundColor,
    onBackground = LightOnBackgroundColor,
    text = LightTextColor,
    border = LightBoarderColor,
    card = LightCardColor
)

val DarkCustomColorsPalette = CustomColor(
    primary = DarkPrimaryColor,
    background = DarkBackgroundColor,
    onBackground = DarkTabUnselectedColor,
    text = DarkTextColor,
    border = DarkBoarderColor,
    card = DarkCardColor
)

val LocalCustomColors = compositionLocalOf { CustomColor() }

val MaterialTheme.customColors: CustomColor
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current
