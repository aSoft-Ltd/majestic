package majestic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class ThemeManager(
    val choices: List<ColorPair> = listOf(
        ColorPair(Color(0xFFFAFAFA), Color(0xFF0061FF)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF673AB7)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF26C6DA)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF26A69A)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFF4CAF50)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFFCDDC39)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFFFFA000)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFFF57C00)),
        ColorPair(Color(0xFFFAFAFA), Color(0xFFFF5722)),
    )
) {
    val state = mutableStateOf(ThemeState.dark())

    fun setMode(m: ThemeMode) {
        val current = state.value
        state.value = current.copy(
            mode = m,
            colors = current.colors.copy(base = m.color)
        )
    }

    fun toggleMode() = setMode(state.value.mode.toggled())

    fun setColor(color: ColorPair) {
        val current = state.value
        state.value = current.copy(
            colors = current.colors.copy(primary = color)
        )
    }
}