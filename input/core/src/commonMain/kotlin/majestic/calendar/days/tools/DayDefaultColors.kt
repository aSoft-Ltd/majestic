package majestic.calendar.days.tools

import androidx.compose.ui.graphics.Color
import majestic.editor.tools.StateColors

data class DayDefaultColors(
    val text: TextColors,
    val background: StateColors
) {
    companion object {
        val Default = DayDefaultColors(
            text = TextColors.Default,
            background = StateColors(
                focused = Color.White,
                unfocused = Color.Transparent
            )
        )
    }
}