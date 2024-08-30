package majestic

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
import majestic.Dimension.PaddingSmall
import majestic.Dimension.TextDefault

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
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        modifier = modifier.height(52.dp)
            .pointerHoverIcon(PointerIcon.Hand),
        colors = ButtonDefaults.buttonColors(
            contentColor = colors.contentColor,
            containerColor = if (showSuccess) colors.successContainerColor else colors.containerColor,
            disabledContainerColor = colors.containerColor.copy(alpha = 0.7f),
            disabledContentColor = colors.contentColor.copy(alpha = 0.7f)
        ),
    ) {
        if (showLoader)
            CircularProgressIndicator(
                modifier = Modifier.width(22.dp)
                    .padding(top = PaddingSmall),
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
                fontSize = TextDefault
            )
    }
}
