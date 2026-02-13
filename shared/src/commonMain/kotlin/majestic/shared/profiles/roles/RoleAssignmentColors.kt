package majestic.shared.profiles.roles

import androidx.compose.ui.graphics.Color
import majestic.dialogs.DialogColors
import majestic.search.SearchColors
import majestic.shared.button.ButtonColors

data class RoleAssignmentColors(
    val modal: DialogColors,
    val search: SearchColors,
    val title: Color,
    val titleIcon: Color,
    val stats: Color,
    val statsIcon: Color,
    val roleTitle: Color,
    val roleDescription: Color,
    val viewPermissions: Color,
    val assignButton: ButtonColors,
    val setupButton: ButtonColors,
    val cancelButton: ButtonColors,
    val confirmButton: ButtonColors,
    val divider: Color
)