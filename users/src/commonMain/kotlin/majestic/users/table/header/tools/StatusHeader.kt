package majestic.users.table.header.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import majestic.shared.users.HeaderInnerColors
import majestic.shared.users.label.table.FilterLabels
import majestic.shared.users.label.table.StatusLabels
import majestic.tooling.onClick
import majestic.users.table.header.tools.filters.FilterButtons
import majestic.users.table.header.tools.filters.FilterSelect
import majestic.users.table.header.tools.filters.FilterSelectColors
import majestic.users.tools.data.UserStatus
import org.jetbrains.compose.resources.DrawableResource

data class HeaderStatusLabels(
    val status: String,
    val filters: FilterLabels,
    val filterStatus: StatusLabels
)

data class StatusCellProperties(
    val colors: HeaderInnerColors,
    val labels: HeaderStatusLabels,
    val downwardCaretIcon: DrawableResource,
    val upwardCaretIcon: DrawableResource,
    val showFilters: Boolean,
)

@Composable
internal fun StatusHeader(
    props: StatusCellProperties,
    modifier: Modifier,
    onFilter: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var container by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(modifier = modifier.onPlaced { container = it.boundsInParent() }) {
        val popColors = props.colors.compPopColors
        val color =
            if (expanded || isHovered) popColors.foreground else popColors.foreground.copy(0.6f)
        val decoration =
            if (expanded || isHovered) TextDecoration.Underline else TextDecoration.None

        Text(
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                .hoverable(interactionSource = interactionSource)
                .onClick { expanded = !expanded },
            text = props.labels.status,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textDecoration = decoration,
            color = color
        )

        if (expanded && props.showFilters) Popup(
            offset = IntOffset(x = 0, y = (container.bottom.toInt() / 2) + 6),
            onDismissRequest = { expanded = false }
        ) {
            Column(
                modifier = Modifier.width(400.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(popColors.background)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val list = listOf(
                    UserStatus.Invited.getLabels(props.labels.filterStatus),
                    UserStatus.Active.getLabels(props.labels.filterStatus),
                    UserStatus.Declined.getLabels(props.labels.filterStatus),
                    UserStatus.Revoked.getLabels(props.labels.filterStatus)
                )
                val selection by remember { mutableStateOf(list.first()) }


                FilterSelect(
                    colors = FilterSelectColors(
                        selector = props.colors.selectColors,
                        pop = props.colors.compPopColors
                    ),
                    icon = props.downwardCaretIcon,
                    modifier = Modifier.fillMaxWidth(),
                    items = list,
                    value = selection,
                    hint = selection,
                    option = { Text(it, color = popColors.foreground, fontSize = 12.sp) }
                )

                FilterButtons(
                    labels = props.labels.filters,
                    colors = props.colors,
                    onCancel = { expanded = false },
                    onFilter = {
                        onFilter("")
                        expanded = false
                    },
                )
            }
        }
    }
}