package com.task.themeal.modules.meal.domain.meal_details

import com.task.themeal.modules.meal.domain.MealRepository

class GetMealDetailsUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke(id: String) = mealRepository.getMealDetails(id)
}