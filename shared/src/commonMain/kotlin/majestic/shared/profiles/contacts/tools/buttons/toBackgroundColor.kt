package majestic.shared.profiles.contacts.tools.buttons

import majestic.buttons.FlatButtonColors

fun FlatButtonColors.toBackgroundColor(
    isHovered: Boolean,
) = if (isHovered) hovered.background else inactive.background