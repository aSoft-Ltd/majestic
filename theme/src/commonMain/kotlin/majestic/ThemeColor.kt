package majestic

sealed interface ThemeColor : Nature {
    val dominant: ColorPack
    val surface: ColorPack

    val mode: ColorMode
        get() = when (this) {
            is LightThemeColor -> LightMode
            is DarkThemeColor -> DarkMode
        }

    fun dominant(pack: ColorPack): ThemeColor = when (this) {
        is LightThemeColor -> copy(dominant = pack)
        is DarkThemeColor -> copy(dominant = pack)
    }
}

internal data class LightThemeColor(
    override val dominant: ColorPack,
    override val surface: ColorPack = ColorPack.surfaceLight()
) : ThemeColor, Light

internal data class DarkThemeColor(
    override val dominant: ColorPack,
    override val surface: ColorPack = ColorPack.surfaceDark()
) : ThemeColor, Dark