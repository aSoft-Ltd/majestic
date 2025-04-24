package majestic.calendar.month.tools.defaults

import androidx.compose.ui.graphics.Color

data class YearDefaultColors(
    val text: Color,
    val background: Color
) {
    companion object {
        val Default = YearDefaultColors(
            text = Color(0xFF6200EE),
            background = Color(0xFF6200EE)
        )
    }
}