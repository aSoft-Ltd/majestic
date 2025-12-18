package majestic.users.tools.colors

import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Light
import majestic.ThemeColor

internal fun ThemeColor.contentColor(orientation: ScreenOrientation) =
    if (orientation is Portrait && mode is Light) dominant.contra.color
    else surface.contra.color