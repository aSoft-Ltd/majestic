package majestic.shared.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.DateRangePickerManager
import majestic.calendar.CalendarPickerColors
import majestic.layouts.Flex
import majestic.layouts.FlexCol
import majestic.layouts.FlexRow

@Composable
fun CalendarRangePicker(
    manager: DateRangePickerManager,
    from: String,
    to: String,
    colors: CalendarPickerColors,
    orientation: ScreenOrientation,
    arrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier,
) = Flex(
    modifier = modifier,
    orientation = orientation,
    row = FlexRow(arrangement = arrangement),
    col = FlexCol(arrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top))
) {
    SideCalendarPicker(
        manager = manager.start,
        modifier = Modifier.flex(1f),
        label = from,
        colors = colors
    )
    SideCalendarPicker(
        manager = manager.end,
        label = to,
        modifier = Modifier.flex(1f),
        colors = colors
    )
}
