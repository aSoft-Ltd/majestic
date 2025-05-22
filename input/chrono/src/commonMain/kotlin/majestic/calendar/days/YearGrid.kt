package majestic.calendar.days

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.NoRippleInteractionSource
import majestic.calendar.CalendarPickerColors.GridColors

@Composable
internal fun YearGrid(
    modifier: Modifier,
    colors: GridColors,
    selected: (Int) -> Boolean,
    height: Int,
    mid: Int,
    onClick: (Int) -> Unit,
    year: @Composable (CellContext<Int>) -> Unit
) {
    val state = remember { LazyGridState() }

    LaunchedEffect(mid) {
        state.scrollToItem(100, -height / 2)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier,
        state = state
    ) {
        items(201, key = { mid - 100 + it }) { index ->
            val interaction = remember { MutableInteractionSource() }
            val hovered by interaction.collectIsHoveredAsState()
            val y = mid - 100 + index
            val ctx = CellContext(
                position = DayPosition.Inside,
                state = when {
                    selected(y) -> DayState.Selected
                    hovered -> DayState.Hovered
                    else -> DayState.Waiting
                },
                value = y,
                modifier = Modifier
                    .hoverable(interaction)
                    .clickable(NoRippleInteractionSource, null) {
                        onClick(y)
                    },
                colors = colors
            )
            year(ctx)
        }
    }
}