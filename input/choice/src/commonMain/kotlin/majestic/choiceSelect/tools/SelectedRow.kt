package majestic.choiceSelect.tools

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SelectedRow(
    colors: majestic.ColorPair,
    selected: List<String>,
    icon: DrawableResource,
    arrowIcon: DrawableResource,
    isExpanded: Boolean,
    enableHover: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val activeHover = enableHover && isHovered
    val fg = if (activeHover) colors.foreground else colors.foreground.copy(0.7f)
    val bg = if (activeHover) colors.foreground.copy(0.1f)
    else colors.foreground.copy(0.05f)
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(300)
    )
    val label = when {
        selected.isEmpty() -> "Select"
        selected.size == 1 -> selected.first()
        else -> "${selected.size} Selected"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .pointerHoverIcon(PointerIcon.Hand)
            .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = fg,
            contentDescription = null
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = fg,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            modifier = Modifier
                .size(16.dp)
                .graphicsLayer { rotationX = rotation },
            painter = painterResource(arrowIcon),
            tint = colors.foreground.copy(0.5f),
            contentDescription = null
        )
    }
}