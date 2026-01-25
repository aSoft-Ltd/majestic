package profiles.contacts.tools.buttons

import majestic.buttons.FlatButtonColors

fun FlatButtonColors.getBackground(
    isHovered: Boolean,
) = if (isHovered) hovered.background else inactive.background