package majestic.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import majestic.ColorPair
import majestic.button.internal.ButtonElement

@Composable
fun Button(
    modifier: Modifier = Modifier.button(),
    label: @Composable ColorPair.() -> Unit,
) {
    val el = modifier.registered() ?: ButtonElement(
        colors = ButtonElement.colors,
        outline = null,
        disabled = true,
        source = remember { MutableInteractionSource() }
    )

    val hovered by el.source.collectIsHoveredAsState()
    val pressed by el.source.collectIsPressedAsState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        el.colors.toResolved(hovered,pressed,el.disabled).label()
    }
}