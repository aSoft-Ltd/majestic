package majestic.shared.users.tools.colors.list

import androidx.compose.ui.graphics.Color
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.tools.colors.toBackground
import majestic.shared.tools.colors.toUserMenuOptionColors
import majestic.shared.users.table.ListItemColors
import majestic.shared.users.table.NameCellColors
import majestic.shared.users.table.UserTableBodyColors
import majestic.shared.users.table.UsersTableRowColors

fun ThemeColor.toTableBodyColors(
    separator: Color,
    orientation: ScreenOrientation
) = UserTableBodyColors(
    separator = separator,
    hovered = this.surface.contra.color.copy(alpha = 0.1f),
    background = this.toBackground,
    row = UsersTableRowColors(
        checkBox = toCheckboxColors(),
        name = NameCellColors(
            surfaceContra = surface.contra.color,
            dominantActual = dominant.actual.color
        ),
        menuOption = toUserMenuOptionColors(orientation = orientation)
    ),
    listItem = ListItemColors(
        surfaceContra = surface.contra.color,
        dominantActual = dominant.actual.color
    )
)