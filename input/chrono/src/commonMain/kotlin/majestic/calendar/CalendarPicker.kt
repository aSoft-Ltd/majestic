package majestic.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import majestic.calendar.days.DayGrid
import majestic.calendar.days.tools.DayColors

@Composable
fun CalendarPicker(
    modifier: Modifier = Modifier,
    labels: CalendarPickerLabels = CalendarPickerLabels.Default,
    colors: CalendarPickerColors = CalendarPickerColors.Default
) {
    var selected by remember { mutableStateOf<LocalDate?>(null) }
    println("Selected: $selected")
    Column(
        modifier = Modifier.background(colors.surface.background).then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 2.dp, alignment = Alignment.CenterVertically),
    ) {
        DayGrid(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            labels = labels.day,
            colors = colors.day,
            month = Month.APRIL,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            year = 2025,
            selected = { selected == it },
            onClick = {
                selected = if (!it.isSameDateAs(selected)) it else null
            }
        )
    }
}

private fun LocalDate?.isSameDateAs(other: LocalDate?): Boolean {
    return this?.year == other?.year && this?.monthNumber == other?.monthNumber && this?.dayOfMonth == other?.dayOfMonth
}