package com.task.themeal.core.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun LoadingContent(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        CircularProgressIndicator()
    } else {
        content()
    }

}
