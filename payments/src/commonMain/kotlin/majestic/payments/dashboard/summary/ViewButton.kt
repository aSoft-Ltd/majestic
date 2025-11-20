package majestic.payments.dashboard.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.tooling.onClick

@Composable
internal fun ViewButton(
    label: String,
    color: Color,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) color.copy(alpha = 0.2f) else color.copy(alpha = 0.05f)
    val textColor = if (isHovered) color else color.copy(alpha = 0.8f)

    Text(
        modifier = Modifier.clip(CircleShape)
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .onClick { onClick() }
            .padding(vertical = 8.dp, horizontal = 10.dp),
        text = label,
        color = textColor,
        fontSize = 12.sp,
        lineHeight = 1.sp
    )
}
