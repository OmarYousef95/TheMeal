package com.task.themeal.modules.meal.data.mappers

import com.task.themeal.MealDetailsEntity
import com.task.themeal.modules.meal.data.dto.MealDetailsDto
import com.task.themeal.modules.meal.data.dto.MealDto
import com.task.themeal.modules.meal.domain.meal_details.MealDetails
import com.task.themeal.modules.meal.domain.meal_list.Meal

fun MealDto.toMeal(): Meal {
    return Meal(
        id = idMeal,
        name = strMeal,
        image = strMealThumb
    )
}

fun MealDetailsDto.toMealDetails(): MealDetails {
    return MealDetails(
        id = idMeal,
        name = strMeal,
        area = strArea,
        instruction = strInstructions,
        image = strMealThumb
    )
}

fun MealDetailsEntity.toMealDetails(): MealDetails {
    return MealDetails(
        id = id,
        name = name,
        area = area,
        instruction = instruction,
        image = image
    )
}