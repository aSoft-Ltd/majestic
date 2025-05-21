package majestic.calendar

class CalendarPickerLabels(
    val day: WeekdayLabels = WeekdayLabels.Default
) {
    companion object {
        val Default by lazy { CalendarPickerLabels() }
    }

    data class WeekdayLabels(
        val sun: String,
        val mon: String,
        val tue: String,
        val wed: String,
        val thu: String,
        val fri: String,
        val sat: String,
    ) {
        companion object {
            val Default by lazy {
                WeekdayLabels("S", "M", "T", "W", "T", "F", "S")
            }
        }
    }
}