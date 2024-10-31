package majestic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ButtonColors(
    val containerColor: Color = Color.Black,
    val contentColor: Color = Color.White,
    val successContainerColor: Color = Color.Black,
    val successContentColor: Color = Color.White,
    val disabledContainerColor: Color = Color.Black.copy(alpha = 0.7f),
    val disabledContentColor: Color = Color.White.copy(alpha = 0.7f)
)

@Composable
fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonColors(),
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true,
    showLoader: Boolean = false,
    showSuccess: Boolean = false,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
) = ActionButton(
    modifier = modifier,
    colors = colors,
    shape = shape,
    enabled = enabled,
    border = border,
    onClick = onClick,
    interactionSource = interactionSource
) {
    if (showLoader)
        CircularProgressIndicator(
            modifier = Modifier.width(22.dp)
                .padding(top = 6.dp),
            color = colors.contentColor
        )
    else if (showSuccess)
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            tint = colors.successContentColor,
            contentDescription = "Success Icon"
        )
    else
        Text(
            text = text,
            fontSize = 16.sp
        )
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonColors(),
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true,
    showSuccess: Boolean = false,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit,
    label: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        border = border,
        modifier = modifier.height(52.dp)
            .pointerHoverIcon(PointerIcon.Hand),
        colors = ButtonDefaults.buttonColors(
            contentColor = colors.contentColor,
            containerColor = if (showSuccess) colors.successContainerColor else colors.containerColor,
            disabledContainerColor = colors.containerColor.copy(alpha = 0.7f),
            disabledContentColor = colors.contentColor.copy(alpha = 0.7f)
        ),
        interactionSource = interactionSource
    ) {
        label()
    }
}
