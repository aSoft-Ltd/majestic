package majestic.tools

import androidx.compose.ui.graphics.Color

private fun overlayChannel(b: Float, a: Float): Float {
    return if (b < 0.5f) {
        2f * a * b
    }
    else {
        1f - (2f * (1f - a) * (1f - b))
    }
}

fun applyOverlayBlend(base: Color, overlay: Color) = Color(
    red = overlayChannel(base.red, overlay.red),
    green = overlayChannel(base.green, overlay.green),
    blue = overlayChannel(base.blue, overlay.blue),
    alpha = base.alpha
)

fun Color.withOverlay(overlay: Color, alpha: Float): Color {
    val blended = applyOverlayBlend(base = this, overlay = overlay)
    return this.mix(blended, alpha)
}