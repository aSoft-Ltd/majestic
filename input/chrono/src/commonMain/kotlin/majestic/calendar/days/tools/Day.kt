package majestic.calendar.days.tools

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class Day(
    val days: List<String>,
    val color: Color,
    val fontSize: TextUnit
) {
    companion object {
        val Default = Day(
            days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}