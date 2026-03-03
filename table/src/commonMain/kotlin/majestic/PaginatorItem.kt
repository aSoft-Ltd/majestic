package majestic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PaginatorItemState(
    val isActive: Boolean,
    val isHovered: Boolean
)

@Composable
fun PaginatorItem(
    modifier: Modifier = Modifier,
    active: Boolean = false,
    onClick: () -> Unit = {},
    colors: PaginatorColors = PaginatorColors(),
    text: String? = null,
    icon: Painter? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val state = PaginatorItemState(isActive = active, isHovered = isHovered)

    val isDirection = icon != null

    val targetBgColor = if (isDirection) {
        if (isHovered) colors.direction.hovered.background else colors.direction.inactive.background
    } else {
        when {
            state.isActive -> colors.page.active.background
            state.isHovered -> colors.page.hovered.background
            else -> colors.page.inactive.background
        }
    }

    val targetFgColor = when {
        state.isActive -> colors.page.active.foreground
        state.isHovered -> colors.page.hovered.foreground
        else -> colors.page.inactive.foreground
    }

    val animationSpec = tween<Color>(durationMillis = 150, easing = EaseInOut)

    val bgColor by animateColorAsState(
        targetValue = targetBgColor,
        animationSpec = animationSpec,
        label = "bgColor"
    )

    val color by animateColorAsState(
        targetValue = targetFgColor,
        animationSpec = animationSpec,
        label = "fgColor"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .padding(5.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClick = onClick
            ),
    ) {
        when {
            text != null -> {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        color = color,
                        lineHeight = 0.1.sp,
                        fontWeight = if (state.isActive) FontWeight.Bold else null
                    )
                }
            }
            icon != null -> {
                Icon(
                    painter = icon,
                    tint = color,
                    contentDescription = null,
                )
            }
        }
    }
}