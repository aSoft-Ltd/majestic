package majestic.users.tools.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.tooling.onClick


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Modal(
    theme: ThemeColor,
    background: Color,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val dialog = rememberDialogColors(theme, background = background, orientation = orientation)
    BasicAlertDialog(
        modifier = modifier //TODO("For modifier that is exposed,the properties need to be called at the calling site, hence place these ones on the callings site before they are removed permanently")
            .clip(RoundedCornerShape(20.dp))
            .background(dialog.containerColor),
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box {
            content()
            // close button
            CloseButton(
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .align(Alignment.TopEnd)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onDismiss)
                    .hoverable(interactionSource = dialog.interactionSource)
                    .background(color = dialog.cancelBackground, shape = CircleShape),
                tint = dialog.cancelContent,
            )
        }
    }
}