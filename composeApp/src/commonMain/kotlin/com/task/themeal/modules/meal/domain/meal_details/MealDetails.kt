package com.task.themeal.modules.meal.domain.meal_details

data class MealDetails(
    val id: String = "",
    val name: String = "",
    val area: String = "",
    val instruction: String = "",
    val image: String? = null
)