package com.task.themeal.modules.meal.domain

import com.task.themeal.core.domain.Result
import com.task.themeal.modules.meal.domain.meal_details.MealDetails
import com.task.themeal.modules.meal.domain.meal_list.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getMealList(tab: String): Result<List<Meal>, Boolean>
    suspend fun getMealDetails(id: String): Result<MealDetails, Boolean>
    suspend fun insertMeal(meal: MealDetails)
    suspend fun getRecentMealList(): Flow<List<MealDetails>>
    suspend fun getRecentMealDetails(id: String): MealDetails?
}