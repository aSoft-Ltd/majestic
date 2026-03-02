package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement

internal class PlusButtonNode() : Modifier.Node()

internal class PlusButtonElement(
    val source: MutableInteractionSource
) : ModifierNodeElement<PlusButtonNode>() {
    override fun create() = PlusButtonNode()

    override fun update(node: PlusButtonNode) {}

    override fun hashCode() = source.hashCode()

    override fun equals(other: Any?) = other is PlusButtonElement && source == other.source
}