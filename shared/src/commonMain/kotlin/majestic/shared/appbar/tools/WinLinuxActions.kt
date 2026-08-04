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
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_close_48
import majestic.icons.ic_expand_48
import majestic.icons.ic_mini_48
import majestic.shared.appbar.colors.toAppBarActionColors
import majestic.shared.appbar.colors.toAppBarColors
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource

@Composable
fun WinLinuxActions(
    theme: ThemeColor,
    orientation: ScreenOrientation,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {},
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    val minInteraction = remember { MutableInteractionSource() }
    val isMinHovered by minInteraction.collectIsHoveredAsState()
    val minColors = theme.toAppBarActionColors(isHovered = isMinHovered)
    val barColors = theme.toAppBarColors(orientation)

    Icon(
        imageVector = vectorResource(Res.drawable.ic_mini_48),
        contentDescription = null,
        tint = barColors.foreground,
        modifier = Modifier.fillMaxHeight()
            .padding(vertical = 1.dp)
            .background(color = minColors.background)
            .hoverable(minInteraction)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onMinimize)
    )

    val maxInteraction = remember { MutableInteractionSource() }
    val isMaxHovered by maxInteraction.collectIsHoveredAsState()
    val maxColors = theme.toAppBarActionColors(isHovered = isMaxHovered)
    Icon(
        imageVector = vectorResource(Res.drawable.ic_expand_48),
        contentDescription = null,
        tint = barColors.foreground,
        modifier = Modifier.fillMaxHeight()
            .padding(vertical = 1.dp)
            .background(color = maxColors.background)
            .hoverable(maxInteraction)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onMaximize)
    )

    val closeInteraction = remember { MutableInteractionSource() }
    val isCloseHovered by closeInteraction.collectIsHoveredAsState()
    val closeColors = theme.toAppBarActionColors(isHovered = isCloseHovered, isClose = true)
    Icon(
        imageVector = vectorResource(Res.drawable.ic_close_48),
        contentDescription = null,
        tint = if (isCloseHovered) closeColors.foreground else barColors.foreground,
        modifier = Modifier.fillMaxHeight()
            .padding(vertical = 1.dp)
            .background(color = closeColors.background)
            .hoverable(closeInteraction)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onClose)
    )
}
