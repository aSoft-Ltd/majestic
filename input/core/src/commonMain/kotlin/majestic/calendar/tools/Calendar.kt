package majestic.calendar.tools

import majestic.calendar.days.tools.DayColors
import majestic.calendar.days.tools.Day
import majestic.calendar.month.tools.defaults.MonthYearColors
import majestic.calendar.month.tools.defaults.MonthYearDefaults
import majestic.calendar.month.tools.defaults.YearColors

data class Colors(
    val day: DayColors,
    val month: MonthYearColors,
    val year: YearColors
) {
    companion object {
        val Default = Colors(
            day = DayColors.Default,
            month = MonthYearColors.Default,
            year = YearColors.Default
        )
    }
}

data class Week(
    val day: Day,
    val monthYear: MonthYearDefaults,
) {
    companion object {
        val Default = Week(
            day = Day.Default,
            monthYear = MonthYearDefaults.Default
        )

    }
}

data class Calendar(
    val colors: Colors,
    val week: Week
) {
    companion object {
        val Default = Calendar(
            colors = Colors.Default,
            week = Week.Default
        )
    }
}