package com.task.themeal

import androidx.compose.ui.window.ComposeUIViewController
import com.task.themeal.app.App
import com.task.themeal.di.setupKoin

fun MainViewController() = ComposeUIViewController(
    configure = { setupKoin() }
) {
    App()
}