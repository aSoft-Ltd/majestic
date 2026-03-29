package majestic.button.appearence

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.headerIconButton(
    color: Color,
    alpha:Float = 0.03f,
    onClick: () -> Unit = { },
): Modifier = translucentButton(color = color, onClick = onClick, alpha = alpha)