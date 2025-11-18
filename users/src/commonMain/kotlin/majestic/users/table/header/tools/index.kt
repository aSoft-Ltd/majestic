package majestic.users.table.header.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import majestic.Checkbox
import majestic.CheckboxColors
import majestic.tooling.onClick
import majestic.users.tools.ColumnLabels
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.UsersStatusLabels
import majestic.users.tools.data.separator
import org.jetbrains.compose.resources.DrawableResource
import symphony.Table
import symphony.columns.Column

data class HeaderColors(
    val mainColor: Color,
    val separator: Color,
    val checkboxColors: CheckboxColors,
    val innerColors: HeaderInnerColors
)

data class HeaderProperties(
    val colors: HeaderColors,
    val showFilters: Boolean,
    val labels: ColumnLabels,
    val filterStatus: UsersStatusLabels,
    val userAvatar: DrawableResource,
    val upwardCaretIcon: DrawableResource,
    val downwardCaretIcon: DrawableResource
)

@Composable
fun RowScope.UsersTableHeader(
    column: Column<UsersData>,
    count: Int,
    weight: Map<Column<UsersData>, Float>,
    props: HeaderProperties,
    table: Table<UsersData>,
) = when (column.key) {
    "checkbox" -> Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = if (count == 0) 20.dp else 0.dp))
            .weight(weight.getValue(column))
            .background(props.colors.mainColor, RoundedCornerShape(topStart = if (count == 0) 20.dp else 0.dp))
            .background(
                props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(topStart = if (count == 0) 20.dp else 0.dp)
            )
            .separator(true, props.colors.separator)
            .padding(vertical = 24.dp, horizontal = 12.dp),
    ) {
        Checkbox(
            selected = table.selector.isCurrentPageSelectedWholly(),
            colors = if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkboxColors.selected else props.colors.checkboxColors.unselected,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkboxColors.selected.background else props.colors.checkboxColors.unselected.background)
                .border(
                    1.dp,
                    color = if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkboxColors.selected.border else props.colors.checkboxColors.unselected.border,
                    RoundedCornerShape(5.dp)
                )
                .onClick { table.selector.toggleSelectionOfCurrentPage() }
        )
    }

    props.labels.name -> NameCellHeader(
        modifier = Modifier.weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        props = NameCellProperties(
            colors = HeaderInnerColors(
                theme = props.colors.innerColors.theme,
                compPopColors = props.colors.innerColors.compPopColors,
                selectColors = props.colors.innerColors.selectColors,
                search = props.colors.innerColors.search,
                menuItem = props.colors.innerColors.menuItem
            ),
            labels = NameCellLabels(
                title = props.labels.name,
                filters = props.labels.filter
            ),
            showFilters = props.showFilters,
            userIcon = props.userAvatar,
            iconDownward = props.downwardCaretIcon,
            iconUpward = props.upwardCaretIcon
        ),
        onFilter = {}
    )

    props.labels.email -> Email(
        props = EmailCellProperties(
            colors = props.colors.innerColors,
            labels = EmailLabels(
                email = props.labels.email,
                filters = props.labels.filter,
            ),
            showFilters = props.showFilters,
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
    )

    props.labels.id -> Identity(
        props = IdentityCellProperties(
            colors = props.colors.innerColors,
            labels = IdentityLabels(
                id = props.labels.id,
                filters = props.labels.filter
            ),
            showFilters = props.showFilters,
            icon = props.upwardCaretIcon
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    props.labels.dateJoined -> DateJoined(
        props = DateJoinedCellProperties(
            colors = props.colors.innerColors,
            labels = DateJoinedLabels(
                dateJoined = props.labels.dateJoined,
                filters = props.labels.filter
            ),
            showFilters = props.showFilters,
            iconUpward = props.upwardCaretIcon,
            iconDownward = props.downwardCaretIcon
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    props.labels.lastActive -> LastActive(
        props = LastActiveCellProperties(
            colors = props.colors.innerColors,
            labels = LastActiveLabels(
                lastActive = props.labels.lastActive,
                filters = props.labels.filter
            ),
            showFilters = props.showFilters,
            iconDownward = props.downwardCaretIcon,
            iconUpward = props.upwardCaretIcon
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )


    props.labels.lastActive -> StatusHeader(
        props = StatusCellProperties(
            colors = props.colors.innerColors,
            labels = StatusLabels(
                status = props.labels.status,
                filters = props.labels.filter,
                filterStatus = props.filterStatus
            ),
            showFilters = props.showFilters,
            downwardCaretIcon = props.downwardCaretIcon,
            upwardCaretIcon = props.upwardCaretIcon
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.mainColor)
            .background(props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    else -> {
        Text(
            text = column.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clip(
                    if (column.key == "action") RoundedCornerShape(topEnd = if (count == 0) 20.dp else 0.dp) else
                        RoundedCornerShape(0.dp)
                )
                .weight(weight.getValue(column))
                .background(props.colors.mainColor)
                .background(
                    props.colors.innerColors.theme.dominant.actual.color.copy(alpha = 0.1f),
                    shape = if (column.key == "action") RoundedCornerShape(topEnd = if (count == 0) 20.dp else 0.dp) else
                        RoundedCornerShape(0.dp)
                )
                .separator(true, props.colors.separator)
                .padding(vertical = 20.dp, horizontal = 12.dp),
            color = props.colors.innerColors.theme.surface.contra.color.copy(0.6f)
        )
    }
}