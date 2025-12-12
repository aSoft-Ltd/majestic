package majestic.users.tools.dialogs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.users.tools.colors.background

internal data class DialogColors(
    val containerColor: Color,
    val contentColor: Color,
    val cancelContent: Color,
    val cancelBackground: Color,
    val interactionSource: MutableInteractionSource,
)

@Composable
internal fun rememberDialogColors(theme: ThemeColor, background: Color, orientation: ScreenOrientation): DialogColors {
    val background = when (theme.mode) {
        is Dark -> theme.background
        is Light -> theme.background.copy(.1f).compositeOver(theme.surface.actual.color)
    }
    val foreground = theme.surface.contra.color
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val foregroundColor = if (isHovered) foreground else foreground.copy(alpha = 0.7f)
    return remember(theme, orientation) {
        DialogColors(
            containerColor = background,
            contentColor = foreground,
            cancelContent = background,
            cancelBackground = foregroundColor.copy(alpha = 0.7f),
            interactionSource = interactionSource,
        )
    }
}