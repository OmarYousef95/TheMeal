package com.task.themeal.di

import com.task.themeal.AppDatabase
import com.task.themeal.modules.meal.data.database.DatabaseDriverFactory
import com.task.themeal.core.data.HttpClientFactory
import com.task.themeal.modules.meal.data.network.RemoteMealDataSource
import com.task.themeal.modules.meal.data.network.RemoteMealDataSourceImpl
import com.task.themeal.modules.meal.data.repository.MealRepositoryImpl
import com.task.themeal.modules.meal.domain.MealRepository
import com.task.themeal.modules.meal.domain.meal_details.GetMealDetailsUseCase
import com.task.themeal.modules.meal.domain.meal_details.InsertMealUseCase
import com.task.themeal.modules.meal.domain.meal_list.GetMealListUseCase
import com.task.themeal.modules.meal.domain.meal_list.GetRecentMealListUseCase
import com.task.themeal.modules.meal.presentation.meal_details.MealDetailsViewModel
import com.task.themeal.modules.meal.presentation.meal_list.MealListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteMealDataSourceImpl).bind<RemoteMealDataSource>()
    singleOf(::MealRepositoryImpl).bind<MealRepository>()

    viewModelOf(::MealListViewModel)
    singleOf(::GetMealListUseCase)
    singleOf(::GetRecentMealListUseCase)

    viewModelOf(::MealDetailsViewModel)
    singleOf(::GetMealDetailsUseCase)
    singleOf(::InsertMealUseCase)


    single {
        AppDatabase(
            driver = get<DatabaseDriverFactory>().createDriver()
        )
    }
}