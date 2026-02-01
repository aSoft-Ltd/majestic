package majestic.shared.tools.colors

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.compositeOver
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.dialogs.DialogColors

@Composable
fun ThemeColor.rememberUserDialogColors(
    orientation: ScreenOrientation
): DialogColors {
    val background = when (this.mode) {
        is Dark -> toBackground
        is Light -> toBackground.copy(.1f).compositeOver(surface.actual.color)
    }
    val foreground = surface.contra.color
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val foregroundColor = if (isHovered) foreground else foreground.copy(alpha = 0.7f)
    return remember(this, orientation) {
        DialogColors(
            containerColor = background,
            contentColor = foreground,
            cancelContent = background,
            cancelBackground = foregroundColor.copy(alpha = 0.7f),
            interactionSource = interactionSource,
        )
    }
}