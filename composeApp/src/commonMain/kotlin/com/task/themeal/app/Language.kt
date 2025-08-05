package com.task.themeal.app

import androidx.compose.ui.text.intl.Locale

const val ARABIC = "ar"
const val ENGLISH = "en"

fun getLanguage(): String = Locale.current.language

expect fun setAppLanguage(lang: String)


