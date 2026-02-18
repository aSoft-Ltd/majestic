package majestic.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.dialogs.DialogColors
import majestic.icons.Res
import majestic.icons.ic_plus_sign
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

fun Modifier.closeButton(
    dialog: DialogColors
) = this
    .padding(start = 8.dp, top = 20.dp, bottom = 20.dp)
    .pointerHoverIcon(PointerIcon.Hand)
    .hoverable(interactionSource = dialog.interactionSource)
    .background(color = Color.Transparent)
    .padding(5.dp)
    .size(35.dp)
    .graphicsLayer { rotationZ = 45f }

@Composable
fun CloseButton(
    modifier: Modifier,
    tint: Color,
) = Icon(
    modifier = modifier,
    painter = painterResource(Res.drawable.ic_plus_sign),
    contentDescription = "Icon",
    tint = tint,
)