package majestic.shared.profiles.roles.utils

import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.RoleColors

internal fun RoleColors.toBackgroundColor(
    orientation: ScreenOrientation,
    hovered: Boolean,
) = when (orientation) {
    is Landscape -> when (hovered) {
        true -> item.backgrounds.landscape.focused
        false -> item.backgrounds.landscape.unfocused
    }

    is Portrait -> item.backgrounds.portrait
}