package majestic.shared.users.tools.colors.list

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

fun ThemeColor.toListCardColors(background: Color) = when (this) {
    is Dark -> ColorPair(
        background = background.copy(.3f),
        foreground = Color.Transparent
    )

    is Light -> ColorPair(
        background = background.copy(.7f),
        foreground = Color.Transparent
    )
}