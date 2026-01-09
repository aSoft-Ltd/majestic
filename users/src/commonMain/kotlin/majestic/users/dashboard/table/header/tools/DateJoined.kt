package majestic.users.dashboard.table.header.tools

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick
import majestic.users.table.header.tools.DateJoinedCellProperties

@Composable
internal fun DateJoined(
    props: DateJoinedCellProperties,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val popColors = props.colors.compPopColors
        val color =
            if (expanded || isHovered) popColors.foreground else popColors.foreground.copy(0.6f)
        val decoration =
            if (expanded || isHovered) TextDecoration.Underline else TextDecoration.None

        Text(
            modifier = Modifier
                .weight(1f)
                .pointerHoverIcon(PointerIcon.Hand)
                .hoverable(interactionSource = interactionSource)
                .onClick { expanded = !expanded },
            text = props.labels.dateJoined,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
            textDecoration = if (props.showFilters) decoration else TextDecoration.None,
            color = color
        )
    }
}