package majestic.calendar.month

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import majestic.calendar.month.tools.YearDropdown
import majestic.calendar.month.tools.defaults.MonthYearDefaults
import majestic.calendar.tools.DatePickerState


@Composable
fun MonthYearPicker(
    state: DatePickerState,
    modifier: Modifier,
    defaults: MonthYearDefaults = MonthYearDefaults.Default,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.size(24.dp).clickable(onClick = { state.onMonthChanged(state.currentMonth.minus(1, DateTimeUnit.MONTH)) }),
            imageVector = defaults.resources.previousMonth,
            contentDescription = "Previous month",
            tint = defaults.colors.tint.previousMonth
        )

        Box {
            Row(
                Modifier
                    .clickable { state.showYearPicker = true }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
            ) {
                Text(
                    "${state.currentMonth.month.name} ${state.currentMonth.year}",
                    style = TextStyle(
                        color = defaults.colors.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Icon(
                    imageVector = defaults.resources.dropDown,
                    contentDescription = "Select year",
                    modifier = Modifier.size(24.dp),
                    tint = defaults.colors.tint.dropDown
                )
            }

            YearDropdown(
                state = state,
                expanded = state.showYearPicker,
                onDismiss = { state.showYearPicker = false },
                defaults = defaults.yearDefaults
            )
        }

        Icon(
            imageVector = defaults.resources.nextMonth,
            tint = defaults.colors.tint.nextMonth,
            contentDescription = "Next month",
            modifier = Modifier.size(24.dp).clickable(onClick = { state.onMonthChanged(state.currentMonth.plus(1, DateTimeUnit.MONTH)) }),
        )
    }
}