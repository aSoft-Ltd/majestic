package majestic.shared.users.table

import androidx.compose.ui.graphics.Color
import majestic.dropdown.DropdownColors
import majestic.shared.tools.table.TableColors

data class UserTableBodyColors(
    val table: TableColors,
    val dropdownColors: DropdownColors,
    val separator: Color,
    val hovered: Color,
    val row: UsersTableRowColors,
    val listItem: ListItemColors
)