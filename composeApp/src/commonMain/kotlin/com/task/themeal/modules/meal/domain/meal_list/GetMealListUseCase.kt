package com.task.themeal.modules.meal.domain.meal_list

import com.task.themeal.modules.meal.domain.MealRepository

class GetMealListUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke(tab: String) = mealRepository.getMealList(tab)
}