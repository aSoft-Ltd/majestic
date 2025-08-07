package majestic

import androidx.compose.ui.graphics.Color

data class ThemeState(
    val mode: ThemeMode,
    val colors: ThemeColors
) {
    companion object {
        private val Default by lazy {
            ColorPair(
                foreground = Color(0xFFFAFAFA),
                background = Color(0xFF0061FF),
            )
        }

        private fun mode(
            mode: ThemeMode,
            primary: ColorPair = Default
        ) = ThemeState(
            mode = mode,
            colors = ThemeColors(
                base = mode.color,
                primary = primary
            )
        )

        fun dark(
            primary: ColorPair = Default
        ) = mode(ThemeMode.Dark, primary)

        fun light(
            primary: ColorPair = Default
        ) = mode(ThemeMode.Light, primary)
    }
}