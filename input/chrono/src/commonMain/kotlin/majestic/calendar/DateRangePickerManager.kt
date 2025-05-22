package majestic.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.datetime.LocalDate

class DateRangePickerManager(
    start: LocalDate? = null,
    end: LocalDate? = null,
    max: LocalDate? = null,
    min: LocalDate? = null,
    val onPicked: (LocalDate, LocalDate) -> Unit
) {
    val start: DatePickerManager by lazy { StartDatePicker(start, min) }
    val end: DatePickerManager by lazy { EndDatePicker(end, max) }

    inner class StartDatePicker(
        initial: LocalDate?,
        min: LocalDate? = null
    ) : DatePickerManager(initial = initial, max = null, min = min) {
        override fun select(date: LocalDate) {
            if (!isAcceptable(date)) return
            if (selected.isSameDateAs(date)) return clear()

            selected = date
            end.min = date
            val s = date
            val e = end.selected
            if (e != null) onPicked(s, e)
        }

        override fun clear() {
            super.clear()
            end.min = null
        }
    }

    inner class EndDatePicker(
        initial: LocalDate?,
        max: LocalDate? = null,
    ) : DatePickerManager(initial = initial, max = max, min = null) {
        override fun select(date: LocalDate) {
            if (!isAcceptable(date)) return
            if (selected.isSameDateAs(date)) return clear()
            selected = date
            start.max = date
            val s = start.selected
            val e = date
            if (s != null) onPicked(s, e)
        }

        override fun clear() {
            super.clear()
            start.max = null
        }
    }
}

@Composable
fun rememberDateRangePickerManager(
    start: LocalDate? = null,
    end: LocalDate? = null,
    max: LocalDate? = null,
    min: LocalDate? = null,
    onPicked: (start: LocalDate, end: LocalDate) -> Unit = { _, _ -> },
) = remember { DateRangePickerManager(start, end, max, min, onPicked) }