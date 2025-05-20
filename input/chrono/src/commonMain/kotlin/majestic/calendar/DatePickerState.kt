package majestic.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Stable
class DatePickerState(initialSelectedDate: LocalDate? = null) {
    var selectedDate: LocalDate? by mutableStateOf(initialSelectedDate)
        private set

    var currentMonth: LocalDate by mutableStateOf(
        initialSelectedDate?.let { LocalDate(it.year, it.month, 1) }
            ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.let {
                LocalDate(it.year, it.month, 1)
            }
    )
        private set

    var showYearPicker: Boolean by mutableStateOf(false)

    val yearRange: IntRange
        get() = (currentMonth.year - 10)..(currentMonth.year + 10)

    fun onDateSelected(date: LocalDate) {
        selectedDate = date
        // Auto-update current month to match new selection
        currentMonth = LocalDate(date.year, date.month, 1)
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
    initialSelectedDate: LocalDate? = null
) = remember(initialSelectedDate) {
    DatePickerState(initialSelectedDate)
}