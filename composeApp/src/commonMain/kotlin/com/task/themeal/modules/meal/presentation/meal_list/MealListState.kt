package com.task.themeal.modules.meal.presentation.meal_list

import com.task.themeal.modules.meal.domain.meal_list.Meal

data class MealListState(
    val selectedTabIndex: Int = 1,
    val seafoodList: List<Meal> = emptyList(),
    val beefList: List<Meal> = emptyList(),
    val recentList: List<Meal> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
)


