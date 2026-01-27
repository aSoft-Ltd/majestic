package majestic.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import majestic.ColorPair
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    colors: ColorPair,
    onDismiss: () -> Unit,
    closeIcon: DrawableResource? = null,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) = BasicAlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
) {
    Box {
        content()

        // close button
        val foreground = colors.foreground
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        val tintColor = if (isHovered) foreground else foreground.copy(alpha = 0.7f)

        if (closeIcon != null) Icon(
            modifier = Modifier.size(40.dp)
                .padding(top = 12.dp, end = 12.dp)
                .align(Alignment.TopEnd)
                .pointerHoverIcon(PointerIcon.Hand)
                .onClick(onDismiss)
                .hoverable(interactionSource)
                .background(color = colors.background, shape = CircleShape)
                .padding(5.dp),
            painter = painterResource(closeIcon),
            tint = tintColor,
            contentDescription = "Close Icon"
        )
    }
}
