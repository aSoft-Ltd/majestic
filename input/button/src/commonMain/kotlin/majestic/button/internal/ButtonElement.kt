package majestic.button.internal

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import majestic.ColorPair
import majestic.button.ButtonParams
import majestic.button.Stroke

internal data class ButtonElement(
    val colors: ButtonParams<ColorPair>,
    val outline: ButtonParams<Stroke?>?,
    val disabled: Boolean = false,
    val source: MutableInteractionSource
) : ModifierNodeElement<ButtonNode>() {

    companion object {
        val colors = ButtonParams(
            default = ColorPair(Color.White, Color.Magenta),
            hovered = ColorPair(Color.Black, Color.White),
            pressed = ColorPair(Color.Black, Color.Yellow),
        )
    }

    override fun create(): ButtonNode = ButtonNode()

    override fun update(node: ButtonNode) {

    }
}