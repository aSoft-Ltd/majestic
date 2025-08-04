package majestic

sealed interface ColorMode : Nature {
    fun toggled(): ColorMode = when (this) {
        LightMode -> DarkMode
        DarkMode -> LightMode
    }
}

data object LightMode : ColorMode, Light
data object DarkMode : ColorMode, Dark