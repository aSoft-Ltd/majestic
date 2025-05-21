package majestic.calendar.days.tools

import androidx.compose.ui.graphics.Color

data class DayColors(
    val text: TextColors,
    val background: StateColors
) {
    data class StateColors(
        val focused: Color,
        val unfocused: Color
    )

    companion object {
        val Default by lazy {
            DayColors(
                text = TextColors.Default,
                background = StateColors(
                    focused = Color.White,
                    unfocused = Color.Transparent
                )
            )
        }
    }
}