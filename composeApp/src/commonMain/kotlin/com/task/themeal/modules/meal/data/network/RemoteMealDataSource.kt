package com.task.themeal.modules.meal.data.network

import com.task.themeal.core.domain.Result
import com.task.themeal.modules.meal.data.dto.MealDetailsListDto
import com.task.themeal.modules.meal.data.dto.MealListDto

interface RemoteMealDataSource {

    suspend fun getMealList(
        tab: String
    ): Result<MealListDto, Boolean>


    suspend fun getMealDetails(id: String): Result<MealDetailsListDto, Boolean>

}