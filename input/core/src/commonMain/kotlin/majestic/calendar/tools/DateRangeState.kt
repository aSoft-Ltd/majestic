package majestic.calendar.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.LocalDate

data class DateRangePickerLabels(
    val from: String,
    val to: String
)

@Stable
class DateRangeState(
    initialFromDate: LocalDate? = null,
    initialToDate: LocalDate? = null,
    val currentDate: LocalDate,
    labels: DateRangePickerLabels
) {
    var showCalendar by mutableStateOf(false)
    var fromDate by mutableStateOf(initialFromDate)
    var toDate by mutableStateOf(initialToDate)
    private val formattedFromDate = derivedStateOf { formatDate(fromDate) }
    private val formattedToDate = derivedStateOf { formatDate(toDate) }

    val labels = derivedStateOf {
        DateRangePickerLabels(
            from = formattedFromDate.value.ifEmpty { labels.from },
            to = formattedToDate.value.ifEmpty { labels.to }
        )
    }

    val fromMonth = derivedStateOf {
        fromDate?.let { LocalDate(it.year, it.month, 1) }
            ?: LocalDate(currentDate.year, currentDate.month, 1)
    }

    val toMonth = derivedStateOf {
        toDate?.let { LocalDate(it.year, it.month, 1) }
            ?: fromDate?.let { LocalDate(it.year, it.month, 1) }
            ?: LocalDate(currentDate.year, currentDate.month, 1)
    }

    private fun formatDate(date: LocalDate?): String {
        return date?.let {
            "${it.dayOfMonth.toString().padStart(2, '0')}-" +
                    "${it.monthNumber.toString().padStart(2, '0')}-" +
                    it.year
        } ?: ""
    }

    fun updateFromDate(date: LocalDate) {
        fromDate = date
        if (toDate != null && date > toDate!!) toDate = null
    }

    fun updateToDate(date: LocalDate) {
        if (fromDate == null || date >= fromDate!!) {
            toDate = date
        }
    }
}

@Composable
fun rememberDateRangeState(currentDate: LocalDate, labels: DateRangePickerLabels) = remember(currentDate) {
    DateRangeState(currentDate = currentDate, labels = labels)
}