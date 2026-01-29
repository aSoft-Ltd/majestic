package majestic.button

import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.unit.Density
import majestic.ColorPair

class ButtonNode(
    val colors: ButtonState<ColorPair>
) : Modifier.Node(), ParentDataModifierNode {
    override fun Density.modifyParentData(parentData: Any?): Any? {
        return colors
    }
}

class ButtonElement(
    val colors: ButtonState<ColorPair>
) : ModifierNodeElement<ButtonNode>() {
    override fun create(): ButtonNode = ButtonNode(colors)

    override fun update(node: ButtonNode) {

    }

    override fun hashCode(): Int = colors.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is ButtonElement) return false
        return colors == other.colors
    }
}

fun Modifier.button(
    colors: ButtonState<ColorPair>
) = then(ButtonElement(colors))