package majestic.shared.appbar.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair

data class AppBarColors(
    val foreground: Color,
    val background: Color,
    val action: ActionColors
) {
    class ActionColors(
        val default: ColorPair,
        val hovered: ColorPair,
        val close: ColorPair,
    )
}
