package majestic.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick

// wrapper for hover effects
@Composable
internal fun <T> DropdownItemWrapper(
    item: DropdownItem<T>,
    mode: DropdownMode,
    onClick: () -> Unit,
    color: Color,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = when {
        mode == DropdownMode.Action && item.isDestructive && hovered -> Color(0xFFEF5350).copy(alpha = 0.07f)
        hovered -> color.copy(alpha = 0.05f)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
            .background(backgroundColor)
            .hoverable(interactionSource = interactionSource)
            .onClick(onClick)
            .pointerHoverIcon(PointerIcon.Hand)
    ) {
        content()
    }
}