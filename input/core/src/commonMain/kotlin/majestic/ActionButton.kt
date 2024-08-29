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

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    contentColor: Color = Color.White,
    containerColor: Color = Color.Black,
    successContentColor: Color = Color.White,
    successContainerColor: Color = Color.Black,
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
            contentColor = contentColor,
            containerColor = if (showSuccess) successContainerColor else containerColor,
            disabledContainerColor = containerColor.copy(alpha = 0.7f),
            disabledContentColor = contentColor.copy(alpha = 0.7f)
        ),
    ) {
        if (showLoader)
            CircularProgressIndicator(
                modifier = Modifier.width(22.dp)
                    .padding(top = PaddingSmall),
                color = contentColor
            )
        else if (showSuccess)
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                tint = successContentColor,
                contentDescription = "Success Icon"
            )
        else
            Text(
                text = text,
                fontSize = TextDefault
            )
    }
}
