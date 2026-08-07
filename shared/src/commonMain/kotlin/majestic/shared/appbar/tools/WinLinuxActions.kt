package majestic.shared.appbar.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.icons.Res
import majestic.icons.ic_close_48
import majestic.icons.ic_expand_48
import majestic.icons.ic_mini_48
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun WinLinuxActions(
    colors: AppBarColors,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {},
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    IconAction(
        colors = colors,
        icon = Res.drawable.ic_mini_48,
        onClick = onMinimize
    )

    IconAction(
        colors = colors,
        icon = Res.drawable.ic_expand_48,
        onClick = onMaximize
    )

    IconAction(
        colors = colors,
        icon = Res.drawable.ic_close_48,
        onClick = onClose,
        isClose = true
    )
}

@Composable
private fun IconAction(
    colors: AppBarColors,
    isClose: Boolean = false,
    onClick: () -> Unit,
    icon: DrawableResource
) {
    val interaction = remember { MutableInteractionSource() }
    val isHovered by interaction.collectIsHoveredAsState()
    val actionColors = when {
        isClose && isHovered -> colors.action.close
        isHovered -> colors.action.hovered
        else -> colors.action.default
    }

    Icon(
        imageVector = vectorResource(icon),
        contentDescription = null,
        tint = if (isHovered && isClose) actionColors.foreground else colors.foreground,
        modifier = Modifier.fillMaxHeight()
            .padding(vertical = 1.dp)
            .background(color = actionColors.background)
            .hoverable(interaction)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onClick)
    )
}
