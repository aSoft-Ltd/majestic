package majestic.users.tools.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

internal fun Modifier.profileBackground(
    theme: ThemeColor,
    isHovered: Boolean,
    orientation: ScreenOrientation,
    shape: Shape = RoundedCornerShape(0.dp)
) = background(
    color = when (orientation) {
        is Landscape -> when (isHovered) {
            true -> theme
                .toBackground.copy(alpha = .3f)
                .compositeOver(theme.surface.contra.color.copy(.05f))

            else -> theme.toBackground.copy(alpha = .5f)
        }

        is Portrait -> when (theme) {
            is Dark -> theme.toBackground.copy(alpha = .5f)
            is Light -> theme.dominant.actual.color
        }
    },
    shape = shape
)
