package majestic.calendar

import androidx.compose.ui.graphics.Color

class CalendarPickerColors(
    val foreground: Color = Color.Black,
    val background: Color = Color.White
) {
    companion object {
        val Default by lazy { CalendarPickerColors() }
    }
}