package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color

fun applyOverlayBlend(base: Color, overlay: Color): Color {
    fun overlayChannel(b: Float, a: Float): Float {
        return if (b < 0.5f) {
            2f * a * b
        } else {
            1f - (2f * (1f - a) * (1f - b))
        }
    }

    return Color(
        red = overlayChannel(base.red, overlay.red),
        green = overlayChannel(base.green, overlay.green),
        blue = overlayChannel(base.blue, overlay.blue),
        alpha = base.alpha
    )
}