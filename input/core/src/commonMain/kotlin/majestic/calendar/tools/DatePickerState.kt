package majestic.calendar.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.LocalDate

@Stable
class DatePickerState(
    initialDate: LocalDate? = null,
    initialMonth: LocalDate
) {
    var selectedDate by mutableStateOf(initialDate)
    var currentMonth by mutableStateOf(initialMonth)
    var showYearPicker by mutableStateOf(false)

    val yearRange: IntRange
        get() = (currentMonth.year - 10)..(currentMonth.year + 10)

    fun onDateSelected(date: LocalDate) {
        selectedDate = date
    }

    fun onMonthChanged(newMonth: LocalDate) {
        currentMonth = newMonth
    }

    fun onYearSelected(year: Int) {
        currentMonth = LocalDate(year, currentMonth.month, 1)
        showYearPicker = false
    }
}

@Composable
fun rememberDatePickerState(
    initialDate: LocalDate? = null,
    initialMonth: LocalDate,
    currentDate: LocalDate
) = remember(initialMonth, currentDate) { DatePickerState(initialDate, initialMonth) }