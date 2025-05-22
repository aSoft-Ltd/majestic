package majestic.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.Month
import majestic.NoRippleInteractionSource
import majestic.calendar.CalendarPickerColors.GridColors
import majestic.calendar.tools.CellContext
import majestic.calendar.tools.DayPosition
import majestic.calendar.tools.DayState

@Composable
internal fun MonthGrid(
    modifier: Modifier,
    colors: GridColors,
    selected: (Month) -> Boolean,
    onClick: (Month) -> Unit,
    month: @Composable RowScope.(CellContext<Month>) -> Unit
) {
    Column(modifier, verticalArrangement = Arrangement.SpaceEvenly) {
        for (quarter in Month.entries.chunked(3)) Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            for (month in quarter) {
                val interaction = remember { MutableInteractionSource() }
                val hovered by interaction.collectIsHoveredAsState()

                val ctx = CellContext(
                    position = DayPosition.Inside,
                    state = when {
                        selected(month) -> DayState.Selected
                        hovered -> DayState.Hovered
                        else -> DayState.Waiting
                    },
                    value = month,
                    pickable = true,
                    modifier = Modifier.weight(1f)
                        .hoverable(interaction)
                        .clickable(NoRippleInteractionSource, null) {
                            onClick(month)
                        },
                    colors = colors
                )
                month(ctx)
            }
        }
    }
}