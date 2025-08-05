package com.task.themeal.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun setupKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)

    }
}