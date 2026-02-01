package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

private val ThemeColor.backgroundColor: Color
    get() {
        val alpha = when (this) {
            is Light -> 0.3f
            is Dark -> 0.2f
        }
        return dominant.actual.color.copy(alpha = alpha).compositeOver(surface.actual.color)
    }

fun ThemeColor.toPopMainColors() = ColorPair(
    background = backgroundColor,
    foreground = surface.contra.color
)

fun ThemeColor.toPopCompColors(): ColorPair {
    val baseComposite = dominant.actual.color.copy(alpha = 0.05f)
        .compositeOver(surface.actual.color)

    val overlayColor = surface.contra.color
    val finalBackground = applyOverlayBlend(baseComposite, overlayColor)

    return ColorPair(
        background = finalBackground,
        foreground = surface.contra.color
    )
}