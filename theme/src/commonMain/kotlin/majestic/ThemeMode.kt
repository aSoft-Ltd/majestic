package majestic

import androidx.compose.ui.graphics.Color

private val white by lazy { Color(0xFFFAFAFA) }
private val black by lazy { Color(0xFF121212) }

enum class ThemeMode(val color: ColorPair) {
    Dark(ColorPair(foreground = white, background = black)),
    Light(ColorPair(foreground = black, background = white));

    fun toggled() = when (this) {
        Dark -> Light
        Light -> Dark
    }
}