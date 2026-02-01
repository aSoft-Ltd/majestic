package majestic.shared.users.tools.colors.profile.header

import majestic.ThemeColor
import majestic.shared.tools.colors.toMenuOptionColors
import majestic.shared.tools.colors.toPopMainColors
import majestic.shared.tools.colors.toSearchFilterColors
import majestic.shared.tools.colors.toSelectColors
import majestic.shared.users.HeaderInnerColors

internal fun ThemeColor.toHeadInnerColors() = HeaderInnerColors(
    theme = this,
    compPopColors = toPopMainColors(),
    selectColors = toSelectColors(),
    search = toSearchFilterColors(),
    menuItem = toMenuOptionColors(true),
)

