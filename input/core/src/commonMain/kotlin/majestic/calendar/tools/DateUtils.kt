package majestic.calendar.tools


import kotlinx.datetime.Month

fun daysInMonth(month: Month, year: Int): Int = when (month) {
    Month.FEBRUARY -> if (year.isLeapYear()) 29 else 28
    Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
    else -> 31
}

fun Int.isLeapYear() = (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)