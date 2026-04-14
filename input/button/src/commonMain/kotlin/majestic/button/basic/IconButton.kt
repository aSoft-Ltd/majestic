package majestic.button.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import majestic.button.Button

@Composable
fun IconButton(
    icon: ImageVector,
    alpha: Float = 1f,
    size: Dp? = null,
    modifier: Modifier = Modifier
) = Button(modifier = modifier) { colors ->
    BasicButtonContent(
        icon = icon,
        alpha = alpha,
        size = size,
        colors = colors
    )
}

@Composable
fun IconButton(
    icon: Painter,
    alpha: Float = 1f,
    size: Dp? = null,
    modifier: Modifier = Modifier
) = Button(modifier = modifier) { colors ->
    BasicButtonContent(
        icon = icon,
        alpha = alpha,
        size = size,
        colors = colors
    )
}