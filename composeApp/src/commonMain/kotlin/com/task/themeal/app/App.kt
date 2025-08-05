package com.task.themeal.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.task.themeal.modules.meal.presentation.meal_details.MealDetailsScreenRoot
import com.task.themeal.modules.meal.presentation.meal_details.MealDetailsViewModel
import com.task.themeal.modules.meal.presentation.meal_list.MealListScreenRoot
import com.task.themeal.modules.meal.presentation.meal_list.MealListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    var language by remember { mutableStateOf(getLanguage()) }

    MealTheme(language = language) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.MealGraph
        ) {
            navigation<Route.MealGraph>(
                startDestination = Route.MealList
            ) {
                composable<Route.MealList> {
                    val viewModel = koinViewModel<MealListViewModel>()

                    MealListScreenRoot(
                        viewModel = viewModel,
                        onMealClick = {
                            navController.navigate(Route.MealDetails(it.id))
                        },
                        onLanguageChanged = {
                            language = it
                        })
                }

                composable<Route.MealDetails> {

                    val viewModel = koinViewModel<MealDetailsViewModel>()

                    MealDetailsScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

