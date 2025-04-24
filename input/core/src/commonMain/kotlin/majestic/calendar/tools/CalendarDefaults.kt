package majestic.calendar.tools

import majestic.calendar.days.tools.DayDefaultColors
import majestic.calendar.days.tools.WeekDaysDefaults
import majestic.calendar.month.tools.defaults.MonthYearColors
import majestic.calendar.month.tools.defaults.MonthYearDefaults
import majestic.calendar.month.tools.defaults.YearDefaultColors
import majestic.calendar.month.tools.defaults.YearDefaults

data class CalendarColors(
    val day: DayDefaultColors,
    val month: MonthYearColors,
    val year: YearDefaultColors
) {
    companion object {
        val Default = CalendarColors(
            day = DayDefaultColors.Default,
            month = MonthYearColors.Default,
            year = YearDefaultColors.Default
        )
    }
}

data class Defaults(
    val weekDaysDefaults: WeekDaysDefaults,
    val monthYearDefaults: MonthYearDefaults,
) {
    companion object {
        val Default = Defaults(
            weekDaysDefaults = WeekDaysDefaults.Default,
            monthYearDefaults = MonthYearDefaults.Default
        )

    }
}

data class CalendarDefaults(
    val colors: CalendarColors,
    val defaults: Defaults
) {
    companion object {
        val Default = CalendarDefaults(
            colors = CalendarColors.Default,
            defaults = Defaults.Default
        )
    }
}