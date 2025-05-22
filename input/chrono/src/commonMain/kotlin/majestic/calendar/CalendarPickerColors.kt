package majestic.calendar

import androidx.compose.ui.graphics.Color
import majestic.colors.ColorPair

data class CalendarPickerColors(
    val surface: ColorPair = ColorPair(foreground = Color.Black, background = Color.White),
    val day: GridColors = GridColors(
        waiting = ColorPair(foreground = surface.foreground, background = Color.Transparent),
        hovered = ColorPair(foreground = Color.White, background = Color.Blue),
        picked = ColorPair(foreground = Color.White, background = Color.Green),
        outside = ColorPair(foreground = Color.Gray, background = Color.Transparent)
    )
) {
    companion object {
        val Default by lazy { CalendarPickerColors() }
    }

    data class GridColors(
        /**
         * When the day is waiting to be picked
         */
        val waiting: ColorPair,
        /**
         * When the day is hovered
         */
        val hovered: ColorPair,
        /**
         * When the day is picked
         */
        val picked: ColorPair,
        /**
         * When the day is outside the current month
         */
        val outside: ColorPair
    )
}