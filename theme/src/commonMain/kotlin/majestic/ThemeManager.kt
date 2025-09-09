package majestic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class ThemeManager(
    val dominants: List<ColorPack> = listOf(
        ColorPack(actual = ActualColor.of(0xFF0061FF), contra = ContraColor.light()),
        ColorPack(actual = ActualColor.of(0xFF673AB7), contra = ContraColor.light()),
        ColorPack(actual = ActualColor.of(0xFF26C6DA), contra = ContraColor.dark()),
        ColorPack(actual = ActualColor.of(0xFF26A69A), contra = ContraColor.light()),
        ColorPack(actual = ActualColor.of(0xFF4CAF50), contra = ContraColor.light()),
        ColorPack(actual = ActualColor.of(0xFFCDDC39), contra = ContraColor.dark()),
        ColorPack(actual = ActualColor.of(0xFFFFA000), contra = ContraColor.dark()),
        ColorPack(actual = ActualColor.of(0xFFF57C00), contra = ContraColor.dark()),
        ColorPack(actual = ActualColor.of(0xFFFF5722), contra = ContraColor.dark()),
    )
) {
    val state = mutableStateOf<ThemeColor>(DarkThemeColor(dominants.first(), ColorPack.surfaceDark()))

    /**
     * Sets the theme mode to either Light or Dark.
     * If the current theme is already in the specified mode, no change is made.
     *
     * e.g
     * ```kotlin
     *     set(Dark) // Sets the theme to Dark mode
     *     set(Light) // Sets the theme to Light mode
     * ```
     */
    fun set(mode: ColorMode) {
        when (mode) {
            is Light -> {
                if (state.value is Light) return
                val current = state.value
                state.value = LightThemeColor(
                    dominant = current.dominant,
                    surface = ColorPack.surfaceLight(),
                )
            }

            is Dark -> {
                if (state.value is Dark) return
                val current = state.value
                state.value = DarkThemeColor(
                    dominant = current.dominant,
                    surface = ColorPack.surfaceDark(),
                )
            }
        }
    }

    fun set(mode: ColorMode, dominant: ColorPack) = when (mode) {
        is Light -> state.value = LightThemeColor(
            dominant = dominant,
            surface = ColorPack.surfaceLight()
        )

        is Dark -> state.value = DarkThemeColor(
            dominant = dominant,
            surface = ColorPack.surfaceDark()
        )
    }

    fun toggleMode() {
        set(state.value.mode.toggled())
    }

    private var index = 0
    fun next() {
        index = (index + 1) % dominants.size
        set(dominants[index])
    }

    fun set(dominant: ColorPack) {
        state.value = when (state.value) {
            is LightThemeColor -> state.value.dominant(dominant)
            is DarkThemeColor -> state.value.dominant(dominant)
        }
    }
}