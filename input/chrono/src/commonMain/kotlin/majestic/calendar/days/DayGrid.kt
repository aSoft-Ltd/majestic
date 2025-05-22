package majestic.calendar.days

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import majestic.NoRippleInteractionSource
import majestic.calendar.CalendarPickerColors
import majestic.calendar.CalendarPickerLabels
import majestic.calendar.tools.daysInMonth

@Composable
internal fun DayGrid(
    modifier: Modifier,
    labels: CalendarPickerLabels.WeekdayLabels,
    colors: CalendarPickerColors.GridColors,
    month: Month,
    year: Int,
    selected: (LocalDate) -> Boolean,
    selectable: (LocalDate) -> Boolean,
    verticalArrangement: Arrangement.Vertical,
    horizontalArrangement: Arrangement.Horizontal,
    onClick: (LocalDate) -> Unit,
    day: @Composable RowScope.(CellContext<LocalDate>) -> Unit
) {
    val daysInCurrentMonth = daysInMonth(month, year)
    val firstDay = LocalDate(year, month, 1)
    val firstDayOfWeek = firstDay.dayOfWeek
    val offset = (firstDayOfWeek.isoDayNumber % 7)

    val prevMonth = firstDay.minus(1, DateTimeUnit.MONTH)
    val daysInPrevMonth = daysInMonth(prevMonth.month, prevMonth.year)
    val prevMonthDays = (daysInPrevMonth - offset + 1)..daysInPrevMonth

    val totalCells = offset + daysInCurrentMonth
    val remainingCells = (7 - (totalCells % 7)) % 7

    // First row
    val days = prevMonthDays.map {
        LocalDate(prevMonth.year, prevMonth.month, it)
    } + List(daysInCurrentMonth) {
        LocalDate(year, month, it + 1)
    } + List(remainingCells) {
        LocalDate(year, month, daysInCurrentMonth).plus(it + 1, DateTimeUnit.DAY)
    }

    Column(modifier, verticalArrangement = verticalArrangement) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = horizontalArrangement) {
            listOf(labels.sun, labels.mon, labels.tue, labels.wed, labels.thu, labels.fri, labels.sat).forEach {
                Box(
                    modifier = Modifier.background(
                        color = colors.outside.background,
                        shape = RoundedCornerShape(8.dp)
                    ).weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        color = colors.outside.foreground
                    )
                }
            }
        }
        for (week in days.chunked(7)) Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = horizontalArrangement) {
            for (day in week) {
                val interaction = remember { MutableInteractionSource() }
                val hovered by interaction.collectIsHoveredAsState()

                val ctx = CellContext(
                    position = if (day.month == month) DayPosition.Inside else DayPosition.Outside,
                    state = when {
                        selected(day) -> DayState.Selected
                        hovered -> DayState.Hovered
                        else -> DayState.Waiting
                    },
                    pickable = selectable(day),
                    value = day,
                    modifier = Modifier.weight(1f)
                        .hoverable(interaction)
                        .clickable(NoRippleInteractionSource, null) {
                            onClick(day)
                        },
                    colors = colors
                )
                day(ctx)
            }
        }
    }
}