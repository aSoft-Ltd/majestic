package majestic.calendar

import kotlinx.datetime.Month

data class CalendarPickerView(
    val month: Month,
    val year: Int,
    val grid: Grid
) {
    enum class Grid {
        Day, Month, Year
    }
}