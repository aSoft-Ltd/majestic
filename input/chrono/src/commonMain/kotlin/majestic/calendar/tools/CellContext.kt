package majestic.calendar.tools

import androidx.compose.ui.Modifier
import majestic.calendar.CalendarPickerColors

class CellContext<out T>(
    val position: DayPosition,
    val pickable: Boolean,
    val state: DayState,
    val value: T,
    internal val colors: CalendarPickerColors.GridColors,
    internal val modifier: Modifier
) {
    fun toColorPair(colors: CalendarPickerColors.GridColors) = when {
        state == DayState.Selected -> colors.picked
        state == DayState.Hovered && pickable -> colors.hovered
        position == DayPosition.Outside -> colors.outside
        pickable -> colors.waiting
        else -> colors.outside
    }
}