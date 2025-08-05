package com.task.themeal.modules.meal.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.task.themeal.AppDatabase
import com.task.themeal.modules.meal.data.database.DatabaseDriverFactory
import com.task.themeal.core.domain.Result
import com.task.themeal.core.domain.map
import com.task.themeal.modules.meal.data.mappers.toMealDetails
import com.task.themeal.modules.meal.data.mappers.toMeal
import com.task.themeal.modules.meal.data.network.RemoteMealDataSource
import com.task.themeal.modules.meal.domain.MealRepository
import com.task.themeal.modules.meal.domain.meal_details.MealDetails
import com.task.themeal.modules.meal.domain.meal_list.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealRepositoryImpl(
    private val remoteMealDataSource: RemoteMealDataSource,
    private val localDatabase: DatabaseDriverFactory
) : MealRepository {

    private val db = AppDatabase(localDatabase.createDriver())
    private val queries = db.mealQueries

    override suspend fun getMealList(tab: String): Result<List<Meal>, Boolean> {
        return remoteMealDataSource.getMealList(tab)
            .map { list -> list.meals.map { meal -> meal.toMeal() } }

    }

    override suspend fun getMealDetails(id: String): Result<MealDetails, Boolean> {
        val meal = getRecentMealDetails(id)
        return if (meal != null) {
            Result.Success(meal)
        } else {
            remoteMealDataSource.getMealDetails(id).map { dto ->
                dto.meals.first().toMealDetails()
            }
        }
    }

    override suspend fun insertMeal(meal: MealDetails) {
        queries.deleteOldestIfLimitExceeded()
        queries.insertMeal(
            id = meal.id,
            name = meal.name,
            area = meal.area,
            instruction = meal.instruction,
            image = meal.image
        )
    }

    override suspend fun getRecentMealList(): Flow<List<MealDetails>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default)
            .map { list ->
                list.map {
                    it.toMealDetails()
                }
            }
    }

    override suspend fun getRecentMealDetails(id: String): MealDetails? {
        return queries.selectById(id).executeAsOneOrNull()?.toMealDetails()
    }
}