package majestic.shared.users.tools.colors.profile.security

import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.ToggleSwitchColors

internal fun ThemeColor.toToggleSwitchColors(): ToggleSwitchColors {
    val foreground = when (this) {
        is Dark -> surface.contra.color
        is Light -> surface.actual.color
    }
    return ToggleSwitchColors(
        on = ColorPair(
            foreground = dominant.contra.color.copy(0.9f),
            background = dominant.actual.color.copy(alpha = 0.8f)
        ),
        off = ColorPair(
            foreground = foreground,
            background = surface.contra.color.copy(alpha = 0.5f)
        )
    )
}