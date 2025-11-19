package majestic.users.table.header.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay
import majestic.tooling.onClick
import majestic.users.table.header.tools.filters.FilterButtons
import majestic.users.table.header.tools.filters.SearchFilter
import majestic.users.table.header.tools.filters.SearchFilterColors
import majestic.users.table.header.tools.filters.SortColumnData
import majestic.users.table.header.tools.filters.SortColumnDataColors
import majestic.users.table.header.tools.filters.SortColumnDataProps
import majestic.users.table.header.tools.filters.searchFilter
import org.jetbrains.compose.resources.DrawableResource

data class LastActiveLabels(
    val lastActive: String,
    val filters: FiltersLabels
)

data class LastActiveCellProperties(
    val colors: HeaderInnerColors,
    val labels: LastActiveLabels,
    val showFilters: Boolean,
    val iconDownward: DrawableResource,
    val iconUpward: DrawableResource
)

@Composable
internal fun LastActive(
    props: LastActiveCellProperties,
    modifier: Modifier = Modifier,
    onFilter: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var container by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier.onPlaced { container = it.boundsInParent() },
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
            text = props.labels.lastActive,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
            textDecoration = if (props.showFilters) decoration else TextDecoration.None,
            color = color
        )
        if (props.showFilters) SortColumnData(
            props = SortColumnDataProps(
                colors = SortColumnDataColors(
                    selector = props.colors.selectColors,
                    pop = props.colors.compPopColors,
                    activeBorder = props.colors.search.border.focused,
                    menuItem = props.colors.menuItem,
                ),
                caretUpward = props.iconUpward,
                caretDownWard = props.iconDownward
            ),
            selected = "",
            items = listOf(props.labels.filters.ascend, props.labels.filters.descend),
            onClick = {}
        )

        if (expanded && props.showFilters) Popup(
            offset = IntOffset(x = 0, y = (container.bottom.toInt() / 2) + 6),
            onDismissRequest = { expanded = false }
        ) {
            val focus = remember { FocusRequester() }
            LaunchedEffect(focus, expanded) {
                delay(1000)
                if (expanded) focus.requestFocus()
            }

            Column(
                modifier = Modifier.width(300.dp).clip(RoundedCornerShape(12.dp))
                    .background(popColors.background).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SearchFilter(
                    modifier = Modifier.searchFilter(popColors.foreground.copy(0.2f))
                        .focusRequester(focus),
                    colors = SearchFilterColors(props.colors.search, props.colors.compPopColors),
                    icon = props.iconDownward,
                    hint = props.labels.filters.searchTitle,
                    focusRequester = focus,
                    onChange = {}
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