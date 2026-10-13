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

fun Color.withNormal(overlay: Color, alpha: Float): Color {
    val inv = 1f - alpha
    return Color(
        red = red * inv + overlay.red * alpha,
        green = green * inv + overlay.green * alpha,
        blue = blue * inv + overlay.blue * alpha,
        alpha = this.alpha
    )
}

fun Color.withOverlay(overlay: Color, alpha: Float): Color {
    val blended = applyOverlayBlend(base = this, overlay = overlay)
    return this.mix(blended, alpha)
}

/** Blends RGB channels at [alpha] strength, preserving the base alpha, like [withOverlay]. */
fun Color.withScreen(overlay: Color, alpha: Float): Color {
    val blended = Color(
        red = 1f - (1f - red) * (1f - overlay.red),
        green = 1f - (1f - green) * (1f - overlay.green),
        blue = 1f - (1f - blue) * (1f - overlay.blue),
        alpha = this.alpha
    )
    return mix(blended, alpha)
}

/** Hard Light is Overlay with the channel threshold taken from the overlay, not the base. */
fun Color.withHardLight(overlay: Color, alpha: Float): Color {
    val blended = Color(
        red = overlayChannel(overlay.red, red),
        green = overlayChannel(overlay.green, green),
        blue = overlayChannel(overlay.blue, blue),
        alpha = this.alpha
    )
    return mix(blended, alpha)
}