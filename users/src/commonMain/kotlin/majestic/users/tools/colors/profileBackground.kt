package majestic.users.tools.colors

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

internal fun Modifier.profileBackground(
    theme: ThemeColor,
    orientation: ScreenOrientation
) = background(
    color = when (orientation) {
        is Landscape -> theme.toBackground.copy(alpha = .5f)
        is Portrait -> when (theme) {
            is Dark -> theme.toBackground.copy(alpha = .5f)
            is Light -> theme.dominant.actual.color
        }
    }
)
