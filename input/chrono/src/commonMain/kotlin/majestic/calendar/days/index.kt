package majestic.calendar.days

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import majestic.calendar.days.tools.DayColors
import majestic.calendar.tools.DatePickerState
import majestic.calendar.tools.daysInMonth

@Composable
internal fun DefaultGrid(
    modifier: Modifier = Modifier,
    colors: DayColors,
    state: DatePickerState
) {
    val currentMonth = state.currentMonth
    val daysInCurrentMonth = daysInMonth(currentMonth.month, currentMonth.year)
    val firstDay = LocalDate(currentMonth.year, currentMonth.month, 1).dayOfWeek
    val offset = (firstDay.isoDayNumber % 7)

    val prevMonth = currentMonth.minus(1, DateTimeUnit.MONTH)
    val daysInPrevMonth = daysInMonth(prevMonth.month, prevMonth.year)
    val prevMonthDays = (daysInPrevMonth - offset + 1)..daysInPrevMonth

    val totalCells = offset + daysInCurrentMonth
    val remainingCells = (7 - (totalCells % 7)) % 7


    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(7)) {
        items(offset) { index ->
            val day = prevMonthDays.first + index
            val date = LocalDate(prevMonth.year, prevMonth.month, day)
            Day(
                day = day,
                isCurrentMonth = false,
                isSelected = date == state.selectedDate,
                onClick = { state.onDateSelected(date) },
                colors = colors
            )
        }

        items(daysInCurrentMonth) { day ->
            val date = LocalDate(currentMonth.year, currentMonth.month, day + 1)
            Day(
                day = day + 1,
                isCurrentMonth = true,
                isSelected = date == state.selectedDate,
                onClick = { state.onDateSelected(date) },
                colors = colors
            )
        }

        items(remainingCells) { day ->
            val nextMonth = currentMonth.plus(1, DateTimeUnit.MONTH)
            val date = LocalDate(nextMonth.year, nextMonth.month, day + 1)
            Day(
                day = day + 1,
                isCurrentMonth = false,
                isSelected = date == state.selectedDate,
                onClick = { state.onDateSelected(date) },
                colors = colors
            )
        }
    }
}