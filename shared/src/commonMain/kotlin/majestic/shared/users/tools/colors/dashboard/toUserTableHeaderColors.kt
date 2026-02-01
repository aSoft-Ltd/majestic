package majestic.shared.users.tools.colors.dashboard

import majestic.ThemeColor
import majestic.shared.users.table.UserTableHeaderColors
import majestic.shared.users.tools.colors.list.toCheckboxColors
import majestic.shared.users.tools.colors.profile.header.toHeadInnerColors

fun ThemeColor.toUserTableHeaderColors() = UserTableHeaderColors(
    separator = surface.contra.color.copy(.4f),
    mainColor = surface.contra.color,
    checkboxColors = toCheckboxColors(),
    innerColors = toHeadInnerColors()
)