package majestic.shared.tools.colors

import androidx.compose.ui.graphics.compositeOver
import majestic.ColorPair
import majestic.ThemeColor

internal fun ThemeColor.toPopCompColors(): ColorPair {
    val baseComposite = dominant.actual.color.copy(alpha = 0.05f)
        .compositeOver(surface.actual.color)

    val overlayColor = surface.contra.color
    val finalBackground = applyOverlayBlend(baseComposite, overlayColor)

    return ColorPair(
        background = finalBackground,
        foreground = surface.contra.color
    )
}