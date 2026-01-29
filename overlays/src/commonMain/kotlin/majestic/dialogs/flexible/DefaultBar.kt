package majestic.dialogs.flexible

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.DialogColors
import majestic.icons.Res
import majestic.icons.ic_plus_sign
import majestic.tooling.onClick
import majestic.tools.CloseButton
import majestic.tools.closeButton
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun DefaultBar(
    orientation: ScreenOrientation,
    dialog: DialogColors,
    onDismiss: () -> Unit,
    modifier: Modifier = when (orientation) {
        is Landscape -> Modifier.fillMaxWidth()
        is Portrait -> Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(dialog.containerColor)
            .padding(horizontal = 8.dp)
    },
    title: String
) {
    when (orientation) {
        Landscape -> Box(modifier = modifier) {
            Icon(
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp)
                    .align(Alignment.TopEnd)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick(onDismiss)
                    .hoverable(interactionSource = dialog.interactionSource)
                    .background(color = dialog.cancelBackground, shape = CircleShape)
                    .padding(5.dp)
                    .size(35.dp)
                    .graphicsLayer { rotationZ = 45f },
                painter = painterResource(Res.drawable.ic_plus_sign),
                contentDescription = "Icon",
                tint = dialog.cancelContent,
            )
        }

        Portrait -> Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CloseButton(
                modifier = Modifier.closeButton(dialog).onClick(onDismiss),
                tint = dialog.cancelContent,
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = dialog.contentColor
            )
        }
    }
}