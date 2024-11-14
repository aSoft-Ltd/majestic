package majestic

import androidx.compose.runtime.mutableStateOf
import majestic.colors.ColorMode
import majestic.colors.options.ACADEMIA_DARK
import majestic.colors.options.ACADEMIA_LIGHT

class ThemeEngine {
    val mode = mutableStateOf(ColorMode.Dark)
    val colors = mutableStateOf(mode.value.toColors())

    private fun ColorMode.toColors() = when (this) {
        ColorMode.Dark -> ACADEMIA_DARK
        ColorMode.Light -> ACADEMIA_LIGHT
    }

    fun setMode(m: ColorMode) {
        mode.value = m
        colors.value = m.toColors()
    }

    fun toggleMode() = setMode(mode.value.toggled())
}