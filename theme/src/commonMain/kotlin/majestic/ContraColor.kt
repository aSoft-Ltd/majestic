package majestic

import androidx.compose.ui.graphics.Color

sealed interface ContraColor : Nature {
    val color: Color

    companion object {
        private val cache by lazy { mutableMapOf<Color, ContraColor>() }

        fun light(color: Color = NeutralColors.white.color): ContraColor = cache.getOrPut(color) { LightContraColor(color) }
        fun dark(color: Color = NeutralColors.black.color): ContraColor = cache.getOrPut(color) { DarkContraColor(color) }
    }
}

private data class LightContraColor(
    override val color: Color
) : ContraColor, Light

private data class DarkContraColor(
    override val color: Color
) : ContraColor, Dark