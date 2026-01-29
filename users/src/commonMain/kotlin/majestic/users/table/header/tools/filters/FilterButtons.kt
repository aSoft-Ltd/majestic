package majestic.users.table.header.tools.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.shared.users.HeaderInnerColors
import majestic.shared.users.label.table.FilterLabels
import majestic.tooling.onClick

@Composable
internal fun FilterButtons(
    labels: FilterLabels,
    colors: HeaderInnerColors,
    onCancel: () -> Unit,
    onFilter: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val popColors = colors.compPopColors

    FilterButton(
        modifier = Modifier.weight(1f)
            .clip(CircleShape)
            .background(popColors.background)
            .padding(vertical = 6.dp, horizontal = 10.dp)
            .onClick { onCancel() },
        label = labels.cancel,
        color = popColors.foreground.copy(0.5f)
    )
    FilterButton(
        modifier = Modifier.weight(1f)
            .clip(CircleShape)
            .background(colors.theme.surface.contra.color.copy(0.7f))
            .padding(vertical = 6.dp, horizontal = 10.dp)
            .onClick { onFilter() },
        label = labels.apply,
        color = colors.theme.surface.actual.color
    )
}