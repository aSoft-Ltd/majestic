package majestic.tools

import androidx.compose.ui.graphics.Color
import majestic.DarkMode
import majestic.LightMode
import majestic.ThemeColor

fun Color.mix(
    color: Color,
    alpha: Float
): Color {
    val inv = 1f - alpha
    return Color(
        red = red * inv + color.red * alpha,
        green = green * inv + color.green * alpha,
        blue = blue * inv + color.blue * alpha,
        alpha = this@mix.alpha * inv + color.alpha * alpha
    )
}

fun ThemeColor.deriveColor(
    dominantActual: Float = 0f,
    dominantVivid: Float = 0f,
    surfaceContra: Float = 0f,
    pureNeutral: Float = 0f
): Color {
    val sa = surface.actual.color
    val da = dominant.actual.color
    val dv = dominant.vivid.color
    val sc = surface.contra.color
    val neutral = when (this.mode) {
        is LightMode -> sa
        is DarkMode -> Color(0f, 0f, 0f, 1f)
    }

    val totalIngredientWeight = dominantActual + dominantVivid + surfaceContra + pureNeutral
    val surfaceActualWeight = (1f - totalIngredientWeight).coerceAtLeast(0f)

    val norm = if (totalIngredientWeight > 1f) totalIngredientWeight else 1f

    return Color(
        red = (sa.red * surfaceActualWeight + da.red * dominantActual + dv.red * dominantVivid + sc.red * surfaceContra + neutral.red * pureNeutral) / norm,
        green = (sa.green * surfaceActualWeight + da.green * dominantActual + dv.green * dominantVivid + sc.green * surfaceContra + neutral.green * pureNeutral) / norm,
        blue = (sa.blue * surfaceActualWeight + da.blue * dominantActual + dv.blue * dominantVivid + sc.blue * surfaceContra + neutral.blue * pureNeutral) / norm,
        alpha = 1f
    )
}

val ThemeColor.base: Color
    get() = surface.actual.color.mix(dominant.actual.color, 0.05f)