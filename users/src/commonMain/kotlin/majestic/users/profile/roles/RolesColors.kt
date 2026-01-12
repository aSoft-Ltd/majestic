package majestic.users.profile.roles

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.users.profile.contacts.tools.DeleteFormColors
import majestic.users.tools.buttons.ButtonAnimateColors
import majestic.users.tools.buttons.FlatButtonColors
import majestic.users.tools.dialogs.DialogColors
import majestic.users.tools.menu.MenuOptionColors

data class RolesColors(
    val background: Color,
    val theme: ThemeColor,
    val campus: CampusColors,
    val roleItem: RoleItemColors,
    val buttonAnimate: ButtonAnimateColors,
    val flatButton: FlatButtonColors,
    val deleteForm: DeleteFormColors,
    val dialog: DialogColors,
)

data class CampusColors(
    val background: Color,
    val theme: ThemeColor,
    val menuOption: MenuOptionColors,
    val iconBackground: Color,
    val iconTint: Color
)

data class RoleItemColors(
    val background: Color,
    val theme: ThemeColor,
    val setupBadgeBackground: Color,
    val setupBadgeText: Color
)