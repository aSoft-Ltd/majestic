package majestic.users.table.header.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay
import majestic.ColorPair
import majestic.SearchDefaultColors
import majestic.SelectColors
import majestic.ThemeColor
import majestic.navigation.MenuItemColors
import majestic.users.table.header.tools.filters.FilterButtons
import majestic.users.table.header.tools.filters.FilterRange
import majestic.users.table.header.tools.filters.FilterRangeColors
import majestic.users.table.header.tools.filters.SearchFilter
import majestic.users.table.header.tools.filters.SearchFilterColors
import majestic.users.table.header.tools.filters.SortColumnData
import majestic.users.table.header.tools.filters.SortColumnDataColors
import majestic.users.table.header.tools.filters.SortColumnDataProps
import majestic.users.table.header.tools.filters.searchFilter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class FiltersLabels(
    val searchTitle: String,
    val dateTime: String,
    val letterRange: String,
    val ascend: String,
    val to: String,
    val from: String,
    val descend: String,
    val apply: String,
    val cancel: String
)

data class HeaderInnerColors(
    val theme: ThemeColor,
    val compPopColors: ColorPair,
    val selectColors: SelectColors,
    val search: SearchDefaultColors,
    val menuItem: MenuItemColors
)

data class NameCellLabels(
    val title: String,
    val filters: FiltersLabels
)

data class NameCellProperties(
    val colors: HeaderInnerColors,
    val labels: NameCellLabels,
    val showFilters: Boolean,
    val userIcon: DrawableResource,
    val iconDownward: DrawableResource,
    val iconUpward: DrawableResource
)

@Composable
internal fun NameCellHeader(
    modifier: Modifier,
    props: NameCellProperties,
    onFilter: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var container by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier.onPlaced { container = it.boundsInParent() },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val popColors = props.colors.compPopColors
        val color = if (expanded || isHovered) popColors.foreground else popColors.foreground.copy(0.6f)
        val decoration = if (expanded || isHovered) TextDecoration.Underline else TextDecoration.None

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(5.dp)),
                imageVector = vectorResource(props.userIcon),
                tint = props.colors.theme.surface.contra.color,
                contentDescription = null,
            )

            Text(
                text = props.labels.title,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = props.colors.theme.surface.contra.color.copy(0.5f)
            )
        }

        var sorted by remember { mutableStateOf("") }
        if (props.showFilters) SortColumnData(
            selected = sorted,
            items = listOf("A - Z", "Z - A"),
            onClick = { sorted = it },
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
                modifier = Modifier.width(300.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(popColors.background)
                    .padding(10.dp),
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

                var from by remember { mutableStateOf("") }
                var to by remember { mutableStateOf("") }
                FilterRange(
                    labels = props.labels.filters,
                    modifier = Modifier.fillMaxWidth(),
                    from = from,
                    to = to,
                    colors = FilterRangeColors(
                        selector = props.colors.selectColors,
                        popCompColors = props.colors.compPopColors,
                        theme = props.colors.theme
                    ),
                    hint = props.labels.filters.letterRange,
                    onFrom = { from = it },
                    onTo = { to = it },
                    caretDown = props.iconDownward
                )
                FilterButtons(
                    colors = props.colors,
                    labels = props.labels.filters,
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