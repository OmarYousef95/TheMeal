package com.task.themeal.modules.meal.presentation.meal_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.themeal.core.domain.onError
import com.task.themeal.core.domain.onSuccess
import com.task.themeal.modules.meal.domain.meal_list.GetMealListUseCase
import com.task.themeal.modules.meal.domain.meal_list.GetRecentMealListUseCase
import com.task.themeal.modules.meal.domain.meal_list.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealListViewModel(
    private val getMealListUseCase: GetMealListUseCase,
    private val getRecentMealListUseCase: GetRecentMealListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()


    fun onAction(action: MealListAction) {
        when (action) {
            is MealListAction.OnMealClick -> {

            }

            is MealListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
                if (action.index == 2) {
                    getRecentMeals()
                } else {
                    getMeal()
                }

            }

            is MealListAction.OnLanguageChanged -> {

            }

            is MealListAction.OnRetryClicked -> {
                getMeal()
            }
        }
    }

    fun getRecentMeals() {
        viewModelScope.launch {
            getRecentMealListUseCase().collect {
                val list = it.map {
                    Meal(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                    )
                }.reversed()
                _state.update {
                    it.copy(recentList = list)
                }
            }
        }

    }


    fun getMeal() {
        viewModelScope.launch {

            val tab = currentMealTab()

            if (hasData(tab)) return@launch

            _state.update {
                it.copy(isLoading = true)
            }

            getMealListUseCase(tab.value)
                .onSuccess { data ->
                    _state.update {
                        when (tab) {
                            MealTab.SEAFOOD -> it.copy(seafoodList = data)
                            MealTab.BEEF -> it.copy(beefList = data)
                        }.copy(isLoading = false, hasError = false)
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            seafoodList = emptyList(),
                            beefList = emptyList(),
                            hasError = error,
                        )
                    }

                }
        }
    }

    private fun currentMealTab(): MealTab =
        if (_state.value.selectedTabIndex == 0) MealTab.SEAFOOD else MealTab.BEEF

    private fun hasData(tab: MealTab): Boolean {
        return when (tab) {
            MealTab.SEAFOOD -> _state.value.seafoodList.isNotEmpty()
            MealTab.BEEF -> _state.value.beefList.isNotEmpty()
        }
    }
}


enum class MealTab(val value: String) {
    SEAFOOD("Seafood"),
    BEEF("Beef"),
}