package majestic.calendar.tools

import majestic.calendar.days.tools.DayColors
import majestic.calendar.days.tools.WeekDays
import majestic.calendar.month.tools.defaults.MonthYearColors
import majestic.calendar.month.tools.defaults.MonthYearDefaults
import majestic.calendar.month.tools.defaults.YearColors

data class CalendarColors(
    val day: DayColors,
    val month: MonthYearColors,
    val year: YearColors
) {
    companion object {
        val Default = CalendarColors(
            day = DayColors.Default,
            month = MonthYearColors.Default,
            year = YearColors.Default
        )
    }
}

data class Weeks(
    val weekDays: WeekDays,
    val monthYear: MonthYearDefaults,
) {
    companion object {
        val Default = Weeks(
            weekDays = WeekDays.Default,
            monthYear = MonthYearDefaults.Default
        )

    }
}

data class CalendarDefaults(
    val colors: CalendarColors,
    val defaults: Weeks
) {
    companion object {
        val Default = CalendarDefaults(
            colors = CalendarColors.Default,
            defaults = Weeks.Default
        )
    }
}