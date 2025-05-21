package majestic.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.calendar.tools.Calendar

@Composable
fun CalendarDatePicker(
    state: DatePickerState,
    modifier: Modifier = Modifier,
    defaults: Calendar = Calendar.Default,
) {
    Calendar(state = state, modifier = modifier, defaults = defaults)
}