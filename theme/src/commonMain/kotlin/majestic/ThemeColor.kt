package majestic

sealed interface ThemeColor : Nature {
    val dominant: ColorPack
    val surface: ColorPack
}

internal data class LightThemeColor(
    override val dominant: ColorPack,
    override val surface: ColorPack
) : ThemeColor, Light

internal data class DarkThemeColor(
    override val dominant: ColorPack,
    override val surface: ColorPack
) : ThemeColor, Light