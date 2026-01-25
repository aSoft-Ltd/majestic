package profiles.contacts.tools.buttons

import majestic.buttons.FlatButtonColors

fun getBackground(
    isHovered: Boolean,
    colors: FlatButtonColors
) = if (isHovered) colors.hovered.background else colors.inactive.background