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

    data class MonthLabels(
        val jan: String,
        val feb: String,
        val mar: String,
        val apr: String,
        val may: String,
        val jun: String,
        val jul: String,
        val aug: String,
        val sep: String,
        val oct: String,
        val nov: String,
        val dec: String
    ) {
        companion object {
            val Default by lazy {
                MonthLabels("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
            }
        }
    }
}