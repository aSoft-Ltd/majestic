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
    val state = mutableStateOf(ThemeState.light(choices.first()))
    val theme = mutableStateOf<ThemeColor>(
        LightThemeColor(
            dominant = dominants.first(),
            surface = ColorPack(
                actual = NeutralColors.white.color,
                contra = ContraColor.dark()
            )
        )
    )

    fun set(mode: ThemeMode, color: ColorPair) {
        state.value = ThemeState(
            mode = mode,
            colors = ThemeColors(
                base = mode.color,
                primary = color
            )
        )
    }

    fun set(mode: ThemeMode) = set(mode, state.value.colors.primary)

    fun toggleMode() = set(state.value.mode.toggled())

    private var idx = 0
    fun next() {
        idx = (idx + 1) % choices.size
        set(choices[idx])
    }

    fun set(color: ColorPair) = set(state.value.mode, color)
}