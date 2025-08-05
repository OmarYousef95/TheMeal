package com.task.themeal.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MealGraph : Route

    @Serializable
    data object MealList : Route

    @Serializable
    data class MealDetails(val id: String) : Route

}