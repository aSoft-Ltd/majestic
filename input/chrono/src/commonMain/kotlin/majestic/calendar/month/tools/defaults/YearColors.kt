package majestic.calendar.month.tools.defaults

import androidx.compose.ui.graphics.Color

data class YearColors(
    val text: Color,
    val background: Color
) {
    companion object {
        val Default = YearColors(
            text = Color(0xFF6200EE),
            background = Color(0xFF6200EE)
        )
    }
}