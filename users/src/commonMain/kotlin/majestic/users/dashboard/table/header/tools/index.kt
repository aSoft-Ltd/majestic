package majestic.users.dashboard.table.header.tools

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
import majestic.users.tools.data.separator
import org.jetbrains.compose.resources.DrawableResource
import symphony.Table
import symphony.columns.Column

data class UsersTableHeaderColors(
    val background: Color,
    val separator: Color,
    val label: Color,
    val checkbox: CheckboxColors,
)

data class UserTableHeaderProps(
    val colors: UsersTableHeaderColors,
    val userAvatar: DrawableResource,
    val labels: ColumnLabels
)

@Composable
fun RowScope.UsersTableHeader(
    column: Column<UsersData>,
    weight: Map<Column<UsersData>, Float>,
    props: UserTableHeaderProps,
    table: Table<UsersData>,
) = when (column.key) {
    "checkbox" -> Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.background)
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
            .background(props.colors.background)
            .separator(true, props.colors.separator)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        colors = NameCellHeaderColors(
            avatar = props.colors.label,
            label = props.colors.label
        ),
        label = props.labels.name,
        avatar = props.userAvatar,
    )

    props.labels.email, props.labels.dateJoined, props.labels.lastActive, props.labels.status -> Box(
        modifier = Modifier
            .weight(weight.getValue(column))
            .background(props.colors.background)
            .separator(true, props.colors.separator)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Text(
            modifier = Modifier,
            text = when (column.key) {
                props.labels.email -> props.labels.email
                props.labels.id -> props.labels.id
                props.labels.dateJoined -> props.labels.dateJoined
                props.labels.lastActive -> props.labels.lastActive
                props.labels.status -> props.labels.status
                else -> ""
            },
            color = props.colors.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    else -> {
        Text(
            text = column.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(weight.getValue(column))
                .background(props.colors.background)
                .separator(true, props.colors.separator)
                .padding(vertical = 20.dp, horizontal = 12.dp),
            color = props.colors.label
        )
    }
}