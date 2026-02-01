package majestic.shared.users.tools.colors.profile.header

import majestic.ThemeColor
import majestic.shared.users.table.UserTableHeaderColors
import majestic.shared.users.tools.colors.list.toCheckboxColors

fun ThemeColor.toUserTableHeaderColors() = UserTableHeaderColors(
    mainColor = surface.actual.color,
    separator = surface.contra.color.copy(alpha = 0.05f),
    checkboxColors = toCheckboxColors(),
    innerColors = toHeadInnerColors()
)