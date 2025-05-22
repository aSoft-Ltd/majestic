package majestic.calendar.days

import androidx.compose.ui.Modifier
import majestic.calendar.CalendarPickerColors

class CellContext<out T>(
    val position: DayPosition,
    val state: DayState,
    val value: T,
    internal val colors: CalendarPickerColors.GridColors,
    internal val modifier: Modifier
) {
    fun toColorPair(colors: CalendarPickerColors.GridColors) = when {
        state == DayState.Selected -> colors.picked
        state == DayState.Hovered -> colors.hovered
        position == DayPosition.Outside -> colors.outside
        else -> colors.waiting
    }
}