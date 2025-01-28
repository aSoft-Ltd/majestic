package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

data class PaginatorItemState(
    val isActive: Boolean,
    val isHovered: Boolean
)

@Composable
fun PaginatorItem(
    modifier: Modifier = Modifier,
    active: Boolean = false,
    colors: PaginatorColors = PaginatorColors(),
    onClick: () -> Unit = {},
    content: @Composable (PaginatorItemState) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val state = PaginatorItemState(isActive = active, isHovered = isHovered)
    val bgColor = when {
        state.isActive -> colors.active.background
        state.isHovered -> colors.hovered.background
        else -> colors.inactive.background
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .padding(5.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                enabled = true,
                onClick = onClick
            ),
    ) { content(state) }
}
