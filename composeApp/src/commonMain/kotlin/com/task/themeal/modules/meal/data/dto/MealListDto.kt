package com.task.themeal.modules.meal.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealListDto(
    val meals: List<MealDto>
)