package majestic.calendar.days.tools

import androidx.compose.ui.graphics.Color

data class TextColors(
    val selected: Color,
    val currentMonth: Color,
    val otherMonth: Color,
) {
    companion object {
        val Default = TextColors(
            selected = Color.Black,
            currentMonth = Color.White,
            otherMonth = Color.Gray
        )
    }
}