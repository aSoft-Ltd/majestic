package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement

internal class ExpandableButtonNode() : Modifier.Node()

internal class ExpandableButtonElement(
    val source: MutableInteractionSource
) : ModifierNodeElement<ExpandableButtonNode>() {
    override fun create() = ExpandableButtonNode()

    override fun update(node: ExpandableButtonNode) {}

    override fun hashCode() = source.hashCode()

    override fun equals(other: Any?) = other is ExpandableButtonElement && source == other.source
}