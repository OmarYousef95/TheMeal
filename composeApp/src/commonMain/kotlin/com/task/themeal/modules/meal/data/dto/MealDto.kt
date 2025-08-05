package com.task.themeal.modules.meal.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String?,
)