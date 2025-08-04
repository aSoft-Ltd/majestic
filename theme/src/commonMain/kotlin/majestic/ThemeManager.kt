package majestic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class ThemeManager(
    val choices: List<ColorPair> = listOf(
        ColorPair(Color(0xFFFAFAFA), Color(0xFF0061FF)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF673AB7)),
        ColorPair(Color(0xFF121212), Color(0xFF26C6DA)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF26A69A)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF4CAF50)),
        ColorPair(Color(0xFF121212), Color(0xFFCDDC39)),
        ColorPair(Color(0xFF121212), Color(0xFFFFA000)),
        ColorPair(Color(0xFF121212), Color(0xFFF57C00)),
        ColorPair(Color(0xFF121212), Color(0xFFFF5722)),
    ),
    val dominants: List<ColorPack> = listOf(
        ColorPack(actual = Color(0xFF0061FF), contra = ContraColor.light()),
        ColorPack(actual = Color(0xFF673AB7), contra = ContraColor.light()),
        ColorPack(actual = Color(0xFF26C6DA), contra = ContraColor.dark()),
        ColorPack(actual = Color(0xFF26A69A), contra = ContraColor.light()),
        ColorPack(actual = Color(0xFF4CAF50), contra = ContraColor.light()),
        ColorPack(actual = Color(0xFFCDDC39), contra = ContraColor.dark()),
        ColorPack(actual = Color(0xFFFFA000), contra = ContraColor.dark()),
        ColorPack(actual = Color(0xFFF57C00), contra = ContraColor.dark()),
        ColorPack(actual = Color(0xFFFF5722), contra = ContraColor.dark()),
    )
) {
    @Deprecated("To be removed after we finish migrating to state")
    val oldState = mutableStateOf(ThemeState.light(choices.first()))

    val state = mutableStateOf<ThemeColor>(LightThemeColor(dominants.first(), ColorPack.surfaceLight()))

    @Deprecated("To be removed after we finish migrating to state")
    fun set(mode: ThemeMode, color: ColorPair) {
        oldState.value = ThemeState(
            mode = mode,
            colors = ThemeColors(
                base = mode.color,
                primary = color
            )
        )
    }

    @Deprecated("To be removed after we finish migrating to state")
    fun set(mode: ThemeMode) = set(mode, oldState.value.colors.primary)

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
        set(oldState.value.mode.toggled())
        set(state.value.mode.toggled())
    }

    private var idx = 0
    private var index = 0
    fun next() {
        idx = (idx + 1) % choices.size
        set(choices[idx])

        index = (index + 1) % dominants.size
        set(dominants[index])
    }

    fun set(color: ColorPair) = set(oldState.value.mode, color)

    fun set(dominant: ColorPack) {
        state.value = when (state.value) {
            is LightThemeColor -> state.value.dominant(dominant)
            is DarkThemeColor -> state.value.dominant(dominant)
        }
    }
}