package majestic.calendar.month.tools.defaults

import androidx.compose.ui.graphics.Color

data class TintColors(
    val nextMonth: Color,
    val previousMonth: Color,
    val dropDown: Color,
) {
    companion object {
        val Default = TintColors(
            nextMonth = Color(0xFF6200EE),
            previousMonth = Color(0xFF6200EE),
            dropDown = Color(0xFF6200EE)
        )
    }
}