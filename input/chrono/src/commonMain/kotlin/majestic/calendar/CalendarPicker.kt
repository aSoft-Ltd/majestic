package majestic.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import majestic.calendar.CalendarPickerView.Grid
import majestic.calendar.days.CellContext
import majestic.calendar.days.DayGrid
import majestic.calendar.days.MonthGrid
import majestic.calendar.days.YearGrid


@Composable
fun CalendarPicker(
    manager: DatePickerManager,
    modifier: Modifier = Modifier,
    labels: CalendarPickerLabels = CalendarPickerLabels.Default,
    colors: CalendarPickerColors = CalendarPickerColors.Default,
    spacing: Dp = 12.dp,
    onChange: (LocalDate?) -> Unit = {},
    day: @Composable RowScope.(CellContext<LocalDate>) -> Unit = { },
) = CalendarPicker(
    manager = manager,
    modifier = modifier,
    labels = labels,
    colors = colors,
    day = day,
    onChange = onChange,
    arrangement = CalendarArrangement(
        vertical = Arrangement.spacedBy(spacing),
        horizontal = Arrangement.spacedBy(spacing)
    )
)

@Composable
fun CalendarPicker(
    manager: DatePickerManager,
    modifier: Modifier = Modifier,
    labels: CalendarPickerLabels = CalendarPickerLabels.Default,
    colors: CalendarPickerColors = CalendarPickerColors.Default,
    arrangement: CalendarArrangement,
    onChange: (LocalDate?) -> Unit = {},
    day: @Composable RowScope.(CellContext<LocalDate>) -> Unit = { }
) {
    Column(
        modifier = Modifier.background(colors.surface.background).then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 2.dp, alignment = Alignment.CenterVertically),
    ) {
        var size by remember { mutableStateOf(IntSize.Zero) }
        when (manager.view.grid) {
            Grid.Day -> DayGrid(
                modifier = Modifier.fillMaxWidth().onPlaced {
                    size = it.size
                }.padding(16.dp),
                labels = labels.day,
                colors = colors.day,
                month = manager.view.month,
                horizontalArrangement = arrangement.horizontal,
                verticalArrangement = arrangement.vertical,
                year = manager.view.year,
                selected = { manager.selected == it },
                day = { context ->
                    Text(
                        modifier = Modifier.cell(context),
                        text = "${context.value.dayOfMonth}",
                        color = context.toColorPair(context.colors).foreground,
                        textAlign = TextAlign.Center
                    )
                },
                onClick = {
                    val v = if (!it.isSameDateAs(manager.selected)) it else null
                    manager.selected = v
                    onChange(v)
                }
            )

            Grid.Month -> MonthGrid(
                modifier = Modifier.size(size.toDpSize(LocalDensity.current)),//.padding(16.dp),
                colors = colors.day,
                selected = { manager.view.month == it },
                onClick = { manager.select(it) },
                month = { context ->
                    Text(
                        modifier = Modifier.cell(context),
                        text = context.value.name.take(3),
                        color = context.toColorPair(context.colors).foreground,
                        textAlign = TextAlign.Center
                    )
                }
            )

            Grid.Year -> YearGrid(
                modifier = Modifier.size(size.toDpSize(LocalDensity.current)),//.padding(16.dp),
                colors = colors.day,
                selected = { manager.view.year == it },
                onClick = { manager.select(it) },
                height = size.height,
                mid = manager.view.year,
                year = { context ->
                    Text(
                        modifier = Modifier.cell(context),
                        text = "${context.value}",
                        color = context.toColorPair(context.colors).foreground,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}

private fun LocalDate?.isSameDateAs(other: LocalDate?): Boolean {
    return this?.year == other?.year && this?.monthNumber == other?.monthNumber && this?.dayOfMonth == other?.dayOfMonth
}

private fun IntSize.toDpSize(density: Density) = with(density) {
    DpSize(width.toDp(), height.toDp())
}