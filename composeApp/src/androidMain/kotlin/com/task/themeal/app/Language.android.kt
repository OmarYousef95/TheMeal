package com.task.themeal.app

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

actual fun setAppLanguage(lang: String) {
    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(lang)
    AppCompatDelegate.setApplicationLocales(appLocale)
}
