package majestic.calendar.month.tools.defaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import majestic.calendar.month.tools.MonthYearResources

data class MonthYearDefaults(
    val colors: MonthYearColors,
    val yearDefaults: YearDefaults,
    val resources: MonthYearResources
) {
    companion object {
        val Default = MonthYearDefaults(
            colors = MonthYearColors.Default,
            yearDefaults = YearDefaults.Default,
            resources = MonthYearResources(
                previousMonth = Icons.AutoMirrored.Filled.ArrowBack,
                nextMonth = Icons.AutoMirrored.Filled.ArrowForward,
                dropDown = Icons.Default.ArrowDropDown
            )
        )
    }
}