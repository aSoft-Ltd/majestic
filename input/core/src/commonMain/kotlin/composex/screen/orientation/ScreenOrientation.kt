package composex.screen.orientation

sealed interface ScreenOrientation

data object Portrait : ScreenOrientation

data object Landscape : ScreenOrientation