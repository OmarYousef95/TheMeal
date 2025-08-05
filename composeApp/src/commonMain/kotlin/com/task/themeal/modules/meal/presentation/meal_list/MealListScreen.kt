package com.task.themeal.modules.meal.presentation.meal_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.task.themeal.app.ARABIC
import com.task.themeal.app.ENGLISH
import com.task.themeal.core.presentation.components.ErrorContent
import com.task.themeal.core.presentation.components.LoadingContent
import com.task.themeal.core.presentation.components.ScreenHeader
import com.task.themeal.core.presentation.customColors
import com.task.themeal.core.presentation.customSpacing
import com.task.themeal.app.getLanguage
import com.task.themeal.modules.meal.domain.meal_list.Meal
import com.task.themeal.modules.meal.presentation.meal_list.components.MealList
import com.task.themeal.app.setAppLanguage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import themeal.composeapp.generated.resources.Res
import themeal.composeapp.generated.resources.beef
import themeal.composeapp.generated.resources.ic_translate
import themeal.composeapp.generated.resources.language
import themeal.composeapp.generated.resources.no_recent_items
import themeal.composeapp.generated.resources.recent
import themeal.composeapp.generated.resources.sea_food


@Composable
fun MealListScreenRoot(
    viewModel: MealListViewModel = koinViewModel(),
    onMealClick: (Meal) -> Unit,
    onLanguageChanged: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MealListScreen(
        state = state,
        onAction = {
            when (it) {
                is MealListAction.OnMealClick -> onMealClick(it.meal)
                is MealListAction.OnLanguageChanged -> onLanguageChanged(it.lang)
                else -> Unit
            }
            viewModel.onAction(it)
        }
    )
}

@Composable
fun MealListScreen(
    state: MealListState,
    onAction: (MealListAction) -> Unit
) {

    val pagerState = rememberPagerState(initialPage = state.selectedTabIndex) { 3 }

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onAction(MealListAction.OnTabSelected(pagerState.currentPage))
    }

    ScreenHeader {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(
                top = MaterialTheme.customSpacing.small,
                start = MaterialTheme.customSpacing.medium
            )
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.customColors.background,
                        shape = RoundedCornerShape(MaterialTheme.customSpacing.small)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColors.border,
                        shape = RoundedCornerShape(MaterialTheme.customSpacing.small)
                    )
                    .padding(MaterialTheme.customSpacing.small)
                    .clickable {
                        val language = if (getLanguage() == ARABIC) ENGLISH else ARABIC
                        setAppLanguage(language)
                        onAction(MealListAction.OnLanguageChanged(language))
                    }
            ) {
                Image(
                    painterResource(Res.drawable.ic_translate),
                    contentDescription = stringResource(Res.string.language),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.customColors.text)
                )

                Text(
                    text = stringResource(Res.string.language),
                    color = MaterialTheme.customColors.text,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(MaterialTheme.customSpacing.small))

        TabRow(
            selectedTabIndex = state.selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = MaterialTheme.customColors.background,
            indicator = { tabPositions ->
                val currentTab = tabPositions[state.selectedTabIndex]
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(currentTab)
                        .fillMaxWidth()
                        .height(MaterialTheme.customSpacing.extraSmall)
                        .padding(horizontal = MaterialTheme.customSpacing.extraLarge)
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.customColors.primary)
                )
            },
            divider = {}
        ) {
            Tab(
                selected = state.selectedTabIndex == 0,
                onClick = {
                    onAction(MealListAction.OnTabSelected(0))
                },
                modifier = Modifier.weight(1f),
                selectedContentColor = MaterialTheme.customColors.onBackground,
            ) {
                Text(
                    text = stringResource(Res.string.sea_food),
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.customSpacing.small),
                    fontWeight = if (state.selectedTabIndex == 0) FontWeight.Bold else null,
                    color = MaterialTheme.customColors.text
                )
            }
            Tab(
                selected = state.selectedTabIndex == 1,
                onClick = {
                    onAction(MealListAction.OnTabSelected(1))
                },
                modifier = Modifier.weight(1f),
                selectedContentColor = MaterialTheme.customColors.onBackground,
            ) {
                Text(
                    text = stringResource(Res.string.beef),
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.customSpacing.small),
                    fontWeight = if (state.selectedTabIndex == 1) FontWeight.Bold else null,
                    color = MaterialTheme.customColors.text
                )
            }

            Tab(
                selected = state.selectedTabIndex == 2,
                onClick = {
                    onAction(MealListAction.OnTabSelected(2))
                },
                modifier = Modifier.weight(1f),
                selectedContentColor = MaterialTheme.customColors.onBackground,
            ) {
                Text(
                    text = stringResource(Res.string.recent),
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.customSpacing.small),
                    fontWeight = if (state.selectedTabIndex == 2) FontWeight.Bold else null,
                    color = MaterialTheme.customColors.text
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.customSpacing.extraSmall))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { pageIndex ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (pageIndex) {
                    0 -> {
                        LoadingContent(state.isLoading) {
                            when {
                                state.hasError -> {
                                    ErrorContent {
                                        onAction(MealListAction.OnRetryClicked)
                                    }
                                }

                                else -> {
                                    MealList(
                                        mealList = state.seafoodList,
                                        onMealClick = {
                                            onAction(MealListAction.OnMealClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }
                            }
                        }

                    }

                    1 -> {
                        LoadingContent(state.isLoading) {
                            when {
                                state.hasError-> {
                                    ErrorContent {
                                        onAction(MealListAction.OnRetryClicked)
                                    }
                                }

                                else -> {
                                    MealList(
                                        mealList = state.beefList,
                                        onMealClick = {
                                            onAction(MealListAction.OnMealClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }
                            }
                        }
                    }

                    2 -> {
                        LoadingContent(state.isLoading) {
                            when {
                                state.recentList.isEmpty() -> {
                                    Text(
                                        text = stringResource(Res.string.no_recent_items),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }

                                else -> {
                                    MealList(
                                        mealList = state.recentList,
                                        onMealClick = {
                                            onAction(MealListAction.OnMealClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}