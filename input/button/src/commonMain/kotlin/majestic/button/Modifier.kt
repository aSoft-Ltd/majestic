package majestic.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.button.internal.ButtonElement

@Composable
fun Modifier.button(
    colors: ButtonParams<ColorPair> = ButtonElement.colors,
    outline: ButtonParams<Stroke?>? = null,
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(8.dp),
    disabled: Boolean = false,
    onClick: () -> Unit = { },
): Modifier {
    val element = remember(colors, outline, shape, disabled, source) {
        ButtonElement(colors, outline, disabled, source)
    }
    val hovered by source.collectIsHoveredAsState()
    val pressed by source.collectIsPressedAsState()

    return then(element)
        .hoverable(element.source)
        .clickable(interactionSource = element.source, indication = null, enabled = !element.disabled, onClickLabel = null, role = null, onClick = onClick)
        .background(color = element.colors.toResolved(hovered, pressed, disabled).background, shape = shape)
        .stroke(outline?.toResolved(hovered, pressed, disabled), shape)
}

private fun Modifier.stroke(s: Stroke?, shape: Shape): Modifier {
    if (s == null) return this
    return this
        .border(width = s.width, color = s.color, shape = shape)
        .padding(s.width)
}

internal fun Modifier.registered(): ButtonElement? {
    var ele: ButtonElement? = null
    any {
        val res = it is ButtonElement
        if (res) ele = it
        res
    }
    return ele
}