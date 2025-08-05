package com.task.themeal.modules.meal.presentation.meal_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.task.themeal.app.Route
import com.task.themeal.core.domain.onError
import com.task.themeal.core.domain.onSuccess
import com.task.themeal.modules.meal.domain.meal_details.GetMealDetailsUseCase
import com.task.themeal.modules.meal.domain.meal_details.InsertMealUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealDetailsViewModel(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val insertMealUseCase: InsertMealUseCase
) : ViewModel() {

    private val id = savedStateHandle.toRoute<Route.MealDetails>().id

    private val _state = MutableStateFlow(MealDetailsState())
    val state = _state.asStateFlow()

    init {
        getMealDetails()
    }

    private fun getMealDetails() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            getMealDetailsUseCase(id)
                .onSuccess { data ->
                    _state.update {
                        it.copy(isLoading = false, mealDetails = data)
                    }
                    insertMeal()
                }.onError { error ->
                    _state.update {
                        it.copy(isLoading = false, mealDetails = null)
                    }
                }
        }

    }

    fun insertMeal() {
        viewModelScope.launch {
            state.value.mealDetails?.let { insertMealUseCase(it) }
        }
    }
}