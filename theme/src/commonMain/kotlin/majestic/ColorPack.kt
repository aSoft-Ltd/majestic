package majestic

import androidx.compose.ui.graphics.Color

data class ColorPack(
    val actual: Color,
    val contra: ContraColor
) {
    companion object {
        fun surfaceLight() = ColorPack(
            actual = NeutralColors.white.color,
            contra = ContraColor.light(NeutralColors.black.color)
        )

        fun surfaceDark() = ColorPack(
            actual = NeutralColors.black.color,
            contra = ContraColor.dark(NeutralColors.white.color)
        )
    }
}