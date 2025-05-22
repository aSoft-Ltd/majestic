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

abstract class DatePickerManager(
    initial: LocalDate?,
    max: LocalDate?,
    min: LocalDate?
) {
    var selected by mutableStateOf(initial)
    var max by mutableStateOf(max)
    var min by mutableStateOf(min)
    internal var view by mutableStateOf(
        CalendarPickerView(
            month = initial?.month ?: LocalDate.fromEpochDays(0).month,
            year = initial?.year ?: LocalDate.fromEpochDays(0).year,
            grid = Day
        )
    )

    open fun select(date: LocalDate) {
        if (!isAcceptable(date)) return
        if (selected.isSameDateAs(date)) {
            clear()
        } else {
            selected = date
        }
    }

    open fun clear() {
        selected = null
    }

    internal fun nextMonth() {
        val last = LocalDate(view.year, view.month, daysInMonth(view.month, view.year))
        val next = last.plus(1, DateTimeUnit.DAY)
        view = view.copy(month = next.month, year = next.year)
    }

    internal fun prevMonth() {
        val last = LocalDate(view.year, view.month, 1)
        val prev = last.minus(1, DateTimeUnit.DAY)
        view = view.copy(month = prev.month, year = prev.year)
    }

    internal fun nextView() {
        view = view.copy(grid = view.grid.next())
    }

    private fun Grid.next() = when (this) {
        Day -> Month
        Month -> Year
        Year -> Day
    }

    internal fun select(month: Month) {
        view = view.copy(grid = Day, month = month)
    }

    internal fun select(year: Int) {
        view = view.copy(grid = Month, year = year)
    }

    protected fun LocalDate?.isSameDateAs(other: LocalDate?): Boolean {
        return this?.year == other?.year && this?.monthNumber == other?.monthNumber && this?.dayOfMonth == other?.dayOfMonth
    }

    fun isSelected(date: LocalDate): Boolean = selected.isSameDateAs(date)

    fun isAcceptable(date: LocalDate): Boolean {
        val mx = max
        val mn = min
        val d = date
        return when {
            mx != null && mn != null -> mn <= d && d <= mx
            mx != null && mn == null -> d <= mx
            mx == null && mn != null -> mn <= d
            else -> true
        }
    }
}

@Composable
fun rememberDatePickerManager(
    initial: LocalDate? = null,
    max: LocalDate? = null,
    min: LocalDate? = null
) = remember { object : DatePickerManager(initial, max, min) {} }