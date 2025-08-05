package com.task.themeal.modules.meal.presentation.meal_details

import com.task.themeal.modules.meal.domain.meal_details.MealDetails


data class MealDetailsState(
    val isLoading: Boolean = true,
    val mealDetails: MealDetails? = null,
)
