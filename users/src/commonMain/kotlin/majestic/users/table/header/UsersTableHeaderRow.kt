package majestic.users.table.header

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.table.header.tools.HeaderProperties
import majestic.users.table.header.tools.UsersTableHeader
import majestic.users.tools.data.UsersData
import symphony.Table
import symphony.columns.Column

@Composable
fun RowScope.UsersTableHeaderRow(
    weight: Map<Column<UsersData>, Float>,
    orientation: ScreenOrientation,
    table: Table<UsersData>,
    selectedAll: Boolean = false,
    selectCount: Int,
    props: HeaderProperties,
    cellHeight: Dp,
    column: Column<UsersData>,
) {
    val theme = props.colors.innerColors.theme
    when {
        orientation is Landscape && selectCount == 0 -> UsersTableHeader(
            column = column,
            weight = weight,
            props = props,
            table = table,
        )

        selectCount > 0 -> {
        }
    }
}