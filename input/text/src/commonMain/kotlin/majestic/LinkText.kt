package majestic

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.TextUnit
import majestic.Dimension.TextDefault

@Composable
fun LinkText(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color(red = 0x00, green = 0x61, blue = 0xFF),
    fontSize: TextUnit = TextDefault
) {
    Text(
        modifier = modifier
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                onClick = onClick
            )
            .pointerHoverIcon(PointerIcon.Hand),
        text = label,
        fontSize = fontSize,
        color = color
    )
}
