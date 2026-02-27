package majestic

data class ColorPack(
    val actual: ActualColor,
    val contra: ContraColor,
    val vivid: ActualColor
) {
    companion object {
        fun surfaceLight() = ColorPack(
            actual = ActualColor(NeutralColors.white.color),
            contra = ContraColor.light(NeutralColors.black.color),
            vivid = ActualColor(NeutralColors.white.color),
        )

        fun surfaceDark() = ColorPack(
            actual = ActualColor(NeutralColors.black.color),
            contra = ContraColor.dark(NeutralColors.white.color),
            vivid = ActualColor(NeutralColors.black.color),
        )
    }
}