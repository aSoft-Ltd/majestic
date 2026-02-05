package majestic.shared.users.table

import androidx.compose.ui.graphics.Color

data class UserTableBodyColors(
    val separator: Color,
    val hovered: Color,
    val background: Color,
    val row: UsersTableRowColors,
    val listItem: ListItemColors
)
