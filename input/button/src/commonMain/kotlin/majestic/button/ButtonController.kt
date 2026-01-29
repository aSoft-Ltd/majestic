package majestic.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Shape
import majestic.ColorPair

class ButtonController(
    val colors: ButtonState<ColorPair>,
    val disabled: Boolean = false,
    val shape: Shape,
    val onClick: () -> Unit
) {
    val source = MutableInteractionSource()

    val state = mutableStateOf(
        ButtonState(
            default = false,
            hovered = false,
            pressed = false,
            disabled = false,
        )
    )

    fun toColorPair() = colors.toColorPair(state.value.hovered, state.value.pressed, disabled)

    fun updateHovered(hovered: Boolean) {
        state.value = state.value.copy(hovered = hovered)
    }

    fun updatePressed(pressed: Boolean) {
        state.value = state.value.copy(pressed = pressed)
    }
}