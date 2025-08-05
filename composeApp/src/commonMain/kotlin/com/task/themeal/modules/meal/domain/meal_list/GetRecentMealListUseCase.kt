package com.task.themeal.modules.meal.domain.meal_list

import com.task.themeal.modules.meal.domain.MealRepository

class GetRecentMealListUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke() = mealRepository.getRecentMealList()
}