package majestic.calendar.days

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.calendar.days.tools.DayColors

@Composable
internal fun Day(
    day: Int,
    isCurrentMonth: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    colors: DayColors = DayColors.Default
) = Box(
    modifier = Modifier
        .aspectRatio(1f)
        .padding(4.dp)
        .background(
            color = when {
                isSelected -> colors.background.focused
                else -> colors.background.unfocused
            },
            shape = RoundedCornerShape(8.dp)
        )
        .clickable(onClick = onClick),
    contentAlignment = Center
) {
    Text(
        text = "$day",
        color = when {
            isSelected -> colors.text.selected
            !isCurrentMonth -> colors.text.otherMonth
            else -> colors.text.currentMonth
        }
    )
}