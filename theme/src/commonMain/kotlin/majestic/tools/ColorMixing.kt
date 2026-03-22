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

    val (scAnchor, pbAnchor, intensity) = when (this.mode) {
        is DarkMode -> {
            Triple(sc, Color(0f, 0f, 0f, 1f), 1.0f)
        }

        is LightMode -> {
            Triple(da, sa, 0.3f)
        }
    }

    val finalDA = dominantActual * intensity
    val finalDV = dominantVivid * intensity
    val finalSC = surfaceContra * intensity

    val totalIngredientWeight = finalDA + finalDV + finalSC + pureNeutral
    val surfaceActualWeight = (1f - totalIngredientWeight).coerceAtLeast(0f)
    val norm = if (totalIngredientWeight > 1f) totalIngredientWeight else 1f

    return Color(
        red = (sa.red * surfaceActualWeight + da.red * finalDA + dv.red * finalDV + scAnchor.red * finalSC + pbAnchor.red * pureNeutral) / norm,
        green = (sa.green * surfaceActualWeight + da.green * finalDA + dv.green * finalDV + scAnchor.green * finalSC + pbAnchor.green * pureNeutral) / norm,
        blue = (sa.blue * surfaceActualWeight + da.blue * finalDA + dv.blue * finalDV + scAnchor.blue * finalSC + pbAnchor.blue * pureNeutral) / norm,
        alpha = 1f
    )
}

val ThemeColor.base: Color
    get() = deriveColor(
        dominantActual = 0.0509f,
        dominantVivid = 0.0000f,
        surfaceContra = 0.0415f,
        pureNeutral = 0.4328f
    )