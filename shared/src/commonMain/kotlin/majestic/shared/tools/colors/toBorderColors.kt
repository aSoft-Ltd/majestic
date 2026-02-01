package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

fun ThemeColor.unFocusedBorder(): Color = when (this.mode) {
    is Dark -> dominant.contra.color.copy(alpha = 0.2f)
    is Light -> dominant.actual.color.copy(alpha = 0.2f)
}

fun ThemeColor.focusedBorder(): Color = when (this.mode) {
    is Dark -> dominant.contra.color
    is Light -> dominant.actual.color
}
