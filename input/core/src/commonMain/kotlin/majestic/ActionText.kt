package majestic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import majestic.Dimension.TextDefault

@Composable
fun ActionText(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    descriptionColor: Color = Color.Unspecified,
    labelColor: Color? = null,
    fontSize: TextUnit = TextDefault,
    enabled: Boolean = true
) {
    val colorBlue = Color(red = 0x00, green = 0x61, blue = 0xFF)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = description ?: "",
            fontSize = fontSize,
            color = descriptionColor
        )
        TextButton(
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
            contentPadding = PaddingValues(0.dp),
            enabled = enabled,
            onClick = onClick,
            interactionSource = NoRippleInteractionSource()
        ) {
            Text(
                text = label,
                fontSize = fontSize,
                color = if (enabled) {
                    labelColor ?: colorBlue
                } else {
                    labelColor?.copy(alpha = 0.7f) ?: colorBlue.copy(alpha = 0.7f)
                }
            )
        }
    }
}
