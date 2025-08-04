package majestic

data class ColorPack(
    val actual: ActualColor,
    val contra: ContraColor
) {
    companion object {
        fun surfaceLight() = ColorPack(
            actual = ActualColor(NeutralColors.white.color),
            contra = ContraColor.light(NeutralColors.black.color)
        )

        fun surfaceDark() = ColorPack(
            actual = ActualColor(NeutralColors.black.color),
            contra = ContraColor.dark(NeutralColors.white.color)
        )
    }
}