package majestic.users.tools.dialogs.flexibleDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.NoRippleInteractionSource
import majestic.users.tools.dialogs.CloseButton
import majestic.users.tools.dialogs.DialogColors

@Composable
internal fun DefaultBar(
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    dialog: DialogColors,
    title: String
) {
    when (orientation) {
        Landscape -> Box(modifier = Modifier.fillMaxWidth()) {
            CloseButton(
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp)
                    .align(Alignment.TopEnd)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable(
                        interactionSource = NoRippleInteractionSource,
                        indication = null,
                        onClick = onDismiss
                    )
                    .hoverable(interactionSource = dialog.interactionSource)
                    .background(color = dialog.cancelBackground, shape = CircleShape),
                tint = dialog.cancelContent,
            )
        }

        Portrait -> Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(dialog.containerColor)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CloseButton(
                modifier = Modifier
                    .padding(start = 8.dp, top = 20.dp, bottom = 20.dp)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable(
                        interactionSource = NoRippleInteractionSource,
                        indication = null,
                        onClick = onDismiss
                    )
                    .hoverable(interactionSource = dialog.interactionSource)
                    .background(color = Color.Transparent),
                tint = dialog.cancelBackground,
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = dialog.contentColor
            )
        }
    }
}