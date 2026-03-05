package majestic.shared.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.DatePickerManager
import majestic.calendar.CalendarPicker
import majestic.calendar.CalendarPickerColors
import majestic.calendar.CalendarPickerLabels
import majestic.calendar.CalendarPickerLabels.WeekdayLabels
import majestic.calendar.WeekStart

@Composable
fun SideCalendarPicker(
    manager: DatePickerManager,
    label: String? = null,
    modifier: Modifier,
    colors: CalendarPickerColors
) = Column(modifier = modifier) {
    if (!label.isNullOrBlank()) Text(
        text = label,
        modifier = Modifier.padding(horizontal = 20.dp),
        color = colors.surface.foreground.copy(0.8f),
    )
    CalenderHeader(
        manager = manager,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
        colors = colors
    )
    CalendarPicker(
        manager = manager,
        weekStart = WeekStart.MONDAY,
        colors = colors,
        labels = CalendarPickerLabels(WeekdayLabels("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"))
    )
}
