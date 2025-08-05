package com.task.themeal.modules.meal.presentation.meal_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.task.themeal.core.presentation.customSpacing
import com.task.themeal.modules.meal.domain.meal_list.Meal

@Composable
fun MealList(
    mealList: List<Meal>,
    onMealClick: (Meal) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(MaterialTheme.customSpacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.customSpacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = mealList,
            key = { it.id }
        ) { meal ->
            MealListItem(
                meal = meal,
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onMealClick(meal)
                }
            )
        }
    }
}