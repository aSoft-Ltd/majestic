package majestic

import androidx.compose.runtime.mutableStateOf

class ThemeManager {
    val state = mutableStateOf(ThemeState.dark())

    fun setMode(m: ThemeMode) {
        val current = state.value
        state.value = current.copy(
            mode = m,
            colors = current.colors.copy(base = m.color)
        )
    }

    fun toggleMode() = setMode(state.value.mode.toggled())
}