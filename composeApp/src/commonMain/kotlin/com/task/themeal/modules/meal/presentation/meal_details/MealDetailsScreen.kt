package com.task.themeal.modules.meal.presentation.meal_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.task.themeal.core.presentation.components.LoadingContent
import com.task.themeal.core.presentation.components.ScreenHeader
import com.task.themeal.core.presentation.customColors
import com.task.themeal.core.presentation.customSpacing
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import themeal.composeapp.generated.resources.Res
import themeal.composeapp.generated.resources.area
import themeal.composeapp.generated.resources.instructions


@Composable
fun MealDetailsScreenRoot(
    viewModel: MealDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MealDetailsScreen(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
fun MealDetailsScreen(
    state: MealDetailsState,
    onBackClick: () -> Unit
) {
    ScreenHeader {
        LoadingContent(isLoading = state.isLoading) {

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = MaterialTheme.customSpacing.medium)
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.customColors.text,
                    modifier = Modifier.clickable {
                        onBackClick()
                    }
                        .padding(vertical = MaterialTheme.customSpacing.small)
                )

                Text(
                    text = state.mealDetails?.name.orEmpty(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.customColors.primary
                )

                Text(
                    text = stringResource(
                        Res.string.area,
                        state.mealDetails?.area.orEmpty()
                    ),
                    color = MaterialTheme.customColors.text
                )

                AsyncImage(
                    model = state.mealDetails?.image.orEmpty(),
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .padding(MaterialTheme.customSpacing.medium)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(MaterialTheme.customSpacing.extraLarge))
                        .height(270.dp),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = stringResource(Res.string.instructions),
                    color = MaterialTheme.customColors.text,
                    style = MaterialTheme.typography.titleMedium
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = state.mealDetails?.instruction.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.customColors.text
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.customSpacing.large))

            }
        }
    }
}

