package com.task.themeal.modules.meal.domain.meal_details

import com.task.themeal.modules.meal.domain.MealRepository

class InsertMealUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke(meal: MealDetails) = mealRepository.insertMeal(meal)
}