package majestic.button.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import majestic.button.Button

@Composable
fun HeaderIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier.Companion
) {
    Button(modifier = modifier) { colors ->
        BasicButtonContent(
            icon = icon,
            colors = colors
        )
    }
}