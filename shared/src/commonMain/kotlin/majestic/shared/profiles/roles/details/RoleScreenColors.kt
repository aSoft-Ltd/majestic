package majestic.shared.profiles.roles.details

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.shared.profiles.roles.assign.form.SearchColors
import majestic.shared.profiles.roles.item.RoleItemColors
import majestic.shared.profiles.tools.BreadCrumbTabColors


data class RoleHeaderColors(
    val background: Color,
    val title: Color,
    val subtitle: Color,
    val icon: Color,
    val search: SearchColors,
    val breadCrumb: BreadCrumbTabColors,
)

data class StationRolesColors(
    val background: Color,
    val header: RoleHeaderColors,
    val item: RoleItemColors,
    val divider: Color
)

data class RoleSummaryColors(
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color
)

data class ResponsibilityColors(
    val background: StateColors,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color
)

data class RoleDetailsColors(
    val background: Color,
    val header: RoleHeaderColors,
    val summary: RoleSummaryColors,
    val responsibility: ResponsibilityColors,
    val divider: Color
)
