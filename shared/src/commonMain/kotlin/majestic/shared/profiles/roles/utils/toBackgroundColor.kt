package majestic.shared.profiles.roles.utils

import majestic.shared.profiles.roles.RoleColors

internal fun RoleColors.toBackgroundColor(
    hovered: Boolean,
) = when (hovered) {
    true -> item.background.focused
    false -> item.background.unfocused
}