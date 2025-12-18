package majestic.users.tools.colors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

internal val ThemeColor.toBackground: Color
    get() {
        val alpha = when (this) {
            is Light -> 0.2f
            is Dark -> 0.1f
        }
        return dominant.actual.color.copy(alpha = alpha).compositeOver(surface.actual.color)
    }

internal val ThemeColor.menuBackgroundColor: Color
    get() {
        return dominant.actual.color.copy(alpha = 0.06f).compositeOver(surface.actual.color)
    }
