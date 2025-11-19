package majestic.users.profile.tabs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.ThemeColor
import majestic.tooling.onClick

@Composable
internal fun Item(
    theme: ThemeColor,
    label: String,
    onClick: () -> Unit,
    selected: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val textColor = when {
        selected -> theme.surface.contra.color
        isHovered -> theme.surface.contra.color
        else -> theme.surface.contra.color.copy(.5f)
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .onClick { onClick() }
            .then(
                if (selected || isHovered) Modifier.drawBehind {
                    val strokeWidth = 3.dp.toPx()
                    val color = ColorPair(
                        background = theme.dominant.actual.color,
                        foreground = theme.dominant.actual.color
                    )
                    drawLine(
                        color = if (selected) color.background else color.foreground.copy(0.2f),
                        start = Offset(0f, size.height - strokeWidth / 2),
                        end = Offset(size.width, size.height - strokeWidth / 2),
                        strokeWidth = strokeWidth
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = label,
            fontSize = 14.sp,
            color = textColor
        )
    }
}