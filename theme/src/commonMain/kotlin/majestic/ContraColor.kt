package majestic

import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

sealed interface ContraColor : Nature {
    val color: Color

    companion object {
        private val cache by lazy { mutableMapOf<Color, ContraColor>() }

        fun light(color: Color = NeutralColors.white.color): ContraColor = cache.getOrPut(color) { LightContraColor(color) }
        fun dark(color: Color = NeutralColors.black.color): ContraColor = cache.getOrPut(color) { DarkContraColor(color) }
    }
}

@JvmInline
private value class LightContraColor(
    override val color: Color
) : ContraColor, Light

@JvmInline
private value class DarkContraColor(
    override val color: Color
) : ContraColor, Dark