package com.task.themeal.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.themeal.core.presentation.customColors
import com.task.themeal.core.presentation.customSpacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import themeal.composeapp.generated.resources.Res
import themeal.composeapp.generated.resources.ic_retry
import themeal.composeapp.generated.resources.retry
import themeal.composeapp.generated.resources.something_went_wrong

@Composable
fun ErrorContent(
    errorMessage: String = stringResource(Res.string.something_went_wrong),
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(MaterialTheme.customSpacing.medium))

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
                .padding(
                    vertical = MaterialTheme.customSpacing.small,
                    horizontal = MaterialTheme.customSpacing.medium
                )
                .clickable {
                    onRetryClicked()
                },
        ) {
            Image(
                painterResource(Res.drawable.ic_retry),
                contentDescription = "Translation",
                colorFilter = ColorFilter.tint(color = MaterialTheme.customColors.text)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.customSpacing.small))

            Text(
                stringResource(Res.string.retry),
                color = MaterialTheme.customColors.text
            )
        }
    }

}