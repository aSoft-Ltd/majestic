package majestic.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import majestic.calendar.CalendarPickerView.Grid
import majestic.calendar.CalendarPickerView.Grid.Day
import majestic.calendar.CalendarPickerView.Grid.Month
import majestic.calendar.CalendarPickerView.Grid.Year
import majestic.calendar.tools.daysInMonth

class CalendarPickerManager(initial: LocalDate?) {
    var selected by mutableStateOf(initial)
    var view by mutableStateOf(
        CalendarPickerView(
            month = initial?.month ?: LocalDate.fromEpochDays(0).month,
            year = initial?.year ?: LocalDate.fromEpochDays(0).year,
            grid = Day
        )
    )

    fun nextMonth() {
        val last = LocalDate(view.year, view.month, daysInMonth(view.month, view.year))
        val next = last.plus(1, DateTimeUnit.DAY)
        view = view.copy(month = next.month, year = next.year)
    }

    fun prevMonth() {
        val last = LocalDate(view.year, view.month, 1)
        val prev = last.minus(1, DateTimeUnit.DAY)
        view = view.copy(month = prev.month, year = prev.year)
    }

    fun nextView() {
        view = view.copy(grid = view.grid.next())
    }

    private fun Grid.next() = when (this) {
        Day -> Month
        Month -> Year
        Year -> Day
    }

    fun select(month: Month) {
        view = view.copy(grid = Day, month = month)
    }

    fun select(year: Int) {
        view = view.copy(grid = Month, year = year)
    }
}

@Composable
fun rememberCalendarPickerManager(initial: LocalDate? = null) = remember { CalendarPickerManager(initial) }