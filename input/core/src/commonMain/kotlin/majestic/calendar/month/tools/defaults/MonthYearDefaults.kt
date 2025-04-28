package majestic.calendar.month.tools.defaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import majestic.calendar.month.tools.CalendarIcons

data class MonthYearDefaults(
    val colors: MonthYearColors,
    val yearDefaults: YearDefaults,
    val resources: CalendarIcons
) {
    companion object {
        val Default = MonthYearDefaults(
            colors = MonthYearColors.Default,
            yearDefaults = YearDefaults.Default,
            resources = CalendarIcons(
                prev = Icons.AutoMirrored.Filled.ArrowBack,
                next = Icons.AutoMirrored.Filled.ArrowForward,
                dropDown = Icons.Default.ArrowDropDown
            )
        )
    }
}