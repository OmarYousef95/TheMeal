package com.task.themeal.modules.meal.presentation.meal_list

import com.task.themeal.modules.meal.domain.meal_list.Meal


sealed interface MealListAction {
    data class OnMealClick(val meal: Meal) : MealListAction
    data class OnTabSelected(val index: Int) : MealListAction
    data object OnRetryClicked : MealListAction
    data class OnLanguageChanged (val lang: String) : MealListAction
}