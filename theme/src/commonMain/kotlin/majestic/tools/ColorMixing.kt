package majestic.tools

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor

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

fun Color.mix(
    other: Color,
    amount: Float
): Color {
    val inv = 1f - amount
    return Color(
        red = red * inv + other.red * amount,
        green = green * inv + other.green * amount,
        blue = blue * inv + other.blue * amount,
        alpha = alpha * inv + other.alpha * amount
    )
}

fun Color.withOverlay(overlay: Color, alpha: Float): Color {
    val blended = applyOverlayBlend(base = this, overlay = overlay)
    return this.mix(blended, alpha)
}

fun ThemeColor.deriveColor(
    dominantActualAmount: Float,
    surfaceContraAmount: Float = 0f
): Color {
    return surface.actual.color
        .mix(dominant.actual.color, dominantActualAmount)
        .mix(surface.contra.color, surfaceContraAmount)
}

val ThemeColor.base: Color
    get() =
        surface.actual.color.mix(dominant.actual.color, 0.05f)