package majestic.button

import androidx.compose.ui.graphics.Color
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

private val RedDestructive = Color(0xFFEF5350)
private val GreenSuccess = Color(0xFF00C853)

val ThemeColor.destructive: Color
    get() = when (this) {
        is Light -> RedDestructive
        is Dark -> RedDestructive
    }

val ThemeColor.success: Color
    get() = when (this) {
        is Light -> GreenSuccess
        is Dark -> GreenSuccess
    }