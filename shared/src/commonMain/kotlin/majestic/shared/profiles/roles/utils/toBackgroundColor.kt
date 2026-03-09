package majestic.shared.profiles.roles.utils

import majestic.shared.profiles.roles.RoleColors

internal fun RoleColors.toBackgroundColor(
    hovered: Boolean,
) = when (hovered) {
    true -> station.background.focused
    false -> station.background.unfocused
}
