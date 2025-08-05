package com.task.themeal.app

import platform.Foundation.NSUserDefaults

actual fun setAppLanguage(lang: String) {
    NSUserDefaults.standardUserDefaults.setObject(
        arrayListOf(lang), forKey = "AppleLanguages"
    )
}