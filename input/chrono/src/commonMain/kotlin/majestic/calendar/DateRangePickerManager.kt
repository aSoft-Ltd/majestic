package majestic.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.datetime.LocalDate

class DateRangePickerManager(
    start: LocalDate? = null,
    end: LocalDate? = null
) {
    val start: DatePickerManager by lazy { StartDatePicker(start) }
    val end: DatePickerManager by lazy { EndDatePicker(end) }

    inner class StartDatePicker(initial: LocalDate?) : DatePickerManager(initial) {

    }

    inner class EndDatePicker(initial: LocalDate?) : DatePickerManager(initial) {

    }
}

@Composable
fun rememberDateRangePickerManager(
    start: LocalDate? = null,
    end: LocalDate? = null
) = remember { DateRangePickerManager(start, end) }