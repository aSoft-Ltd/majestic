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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import majestic.Checkbox
import majestic.shared.users.HeaderInnerColors
import majestic.shared.users.label.table.ColumnLabels
import majestic.shared.users.label.table.StatusLabels
import majestic.shared.users.table.UserTableHeaderColors
import majestic.shared.users.tools.UsersData
import majestic.tooling.onClick
import majestic.users.tools.data.separator
import org.jetbrains.compose.resources.DrawableResource
import symphony.Table
import symphony.columns.Column


data class HeaderProperties(
    val colors: UserTableHeaderColors,
    val showFilters: Boolean,
    val labels: ColumnLabels,
    val filterStatus: StatusLabels,
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
            .background(
                props.colors.main,
                RoundedCornerShape(topStart = if (count == 0) 20.dp else 0.dp)
            )
            .background(
                props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(topStart = if (count == 0) 20.dp else 0.dp)
            )
            .separator(true, props.colors.separator)
            .padding(vertical = 24.dp, horizontal = 12.dp),
    ) {
        Checkbox(
            selected = table.selector.isCurrentPageSelectedWholly(),
            colors = if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkbox.selected else props.colors.checkbox.unselected,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkbox.selected.background else props.colors.checkbox.unselected.background)
                .border(
                    1.dp,
                    color = if (table.selector.isCurrentPageSelectedWholly()) props.colors.checkbox.selected.border else props.colors.checkbox.unselected.border,
                    RoundedCornerShape(5.dp)
                )
                .onClick { table.selector.toggleSelectionOfCurrentPage() }
        )
    }

    props.labels.name -> NameCellHeader(
        modifier = Modifier.weight(weight.getValue(column))
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        props = NameCellProperties(
            colors = HeaderInnerColors(
                theme = props.colors.inner.theme,
                compPopColors = props.colors.inner.compPopColors,
                selectColors = props.colors.inner.selectColors,
                search = props.colors.inner.search,
                menuItem = props.colors.inner.menuItem
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
            colors = props.colors.inner,
            labels = EmailLabels(
                email = props.labels.email,
                filters = props.labels.filter,
            ),
            showFilters = props.showFilters,
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
    )

    props.labels.id -> Identity(
        props = IdentityCellProperties(
            colors = props.colors.inner,
            labels = IdentityLabels(
                id = props.labels.id,
                filters = props.labels.filter
            ),
            showFilters = props.showFilters,
            icon = props.upwardCaretIcon
        ),
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    props.labels.dateJoined -> DateJoined(
        props = DateJoinedCellProperties(
            colors = props.colors.inner,
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
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    props.labels.lastActive -> LastActive(
        props = LastActiveCellProperties(
            colors = props.colors.inner,
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
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )


    props.labels.lastActive -> StatusHeader(
        props = StatusCellProperties(
            colors = props.colors.inner,
            labels = HeaderStatusLabels(
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
            .background(props.colors.main)
            .background(props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f))
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        onFilter = { }
    )

    else -> Box(
        modifier = Modifier
            .weight(weight.getValue(column))
            .clip(
                shape = if (column.key == "action") RoundedCornerShape(
                    topEnd = if (count == 0) 20.dp else 0.dp
                )
                else RoundedCornerShape(0.dp)
            )
            .background(props.colors.main)
            .background(
                props.colors.inner.theme.dominant.actual.color.copy(alpha = 0.1f),
                shape = if (column.key == "action") RoundedCornerShape(topEnd = if (count == 0) 20.dp else 0.dp) else
                    RoundedCornerShape(0.dp)
            )
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        contentAlignment = when (column.key) {
            props.labels.permission, props.labels.roles -> Alignment.Center
            else -> Alignment.CenterStart
        }
    ) {
        Text(
            text = column.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier,
            color = props.colors.inner.theme.surface.contra.color.copy(0.6f)
        )
    }
}