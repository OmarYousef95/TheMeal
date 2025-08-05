package com.task.themeal.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.task.themeal.core.presentation.DarkCustomColorsPalette
import com.task.themeal.core.presentation.LightCustomColorsPalette
import com.task.themeal.core.presentation.LocalCustomColors
import com.task.themeal.core.presentation.LocalCustomSpacing
import com.task.themeal.core.presentation.Spacing

@Composable
fun MealTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    language: String = getLanguage(),
    content: @Composable() () -> Unit,
) {
    val customColorsPalette =
        if (useDarkTheme) DarkCustomColorsPalette
        else LightCustomColorsPalette


    CompositionLocalProvider(
        LocalCustomColors provides customColorsPalette,
        LocalCustomSpacing provides Spacing(),
        LocalLayoutDirection provides if (language == ARABIC) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        MaterialTheme(
            content = content
        )
    }
}