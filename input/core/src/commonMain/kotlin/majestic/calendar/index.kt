package majestic.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.calendar.tools.DatePickerState
import majestic.calendar.days.DefaultGrid
import majestic.calendar.days.WeekDays
import majestic.calendar.month.MonthYearPicker
import majestic.calendar.tools.CalendarDefaults

@Composable
fun Calendar(
    state: DatePickerState,
    modifier: Modifier = Modifier,
    defaults: CalendarDefaults = CalendarDefaults.Default,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 2.dp, alignment = Alignment.CenterVertically),
    ) {
        MonthYearPicker(state = state, modifier = Modifier.fillMaxWidth(), defaults = defaults.defaults.monthYear)
        WeekDays(modifier = Modifier.weight(1f), defaults = defaults.defaults.weekDays)
        DefaultGrid(modifier = Modifier.height(240.dp).fillMaxWidth(), state = state, colors = defaults.colors.day)
    }
}