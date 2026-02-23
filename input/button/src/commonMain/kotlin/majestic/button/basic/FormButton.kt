package majestic.button.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import majestic.button.Button

@Composable
fun FormButton(icon: ImageVector, loading: Boolean = false, text: String, modifier: Modifier = Modifier) {
    Button(modifier = modifier) { colors ->
        BasicButtonContent(
            text = text,
            loading = loading,
            leadingIcon = icon,
            fontSize = 14.sp,
            colors = colors,
        )
    }
}

@Composable
fun FormButton(icon: ImageVector, loading: Boolean = false, modifier: Modifier = Modifier) {
    Button(modifier = modifier) { colors ->
        BasicButtonContent(
            icon = icon,
            loading = loading,
            colors = colors,
        )
    }
}

@Composable
fun FormButton(text: String, loading: Boolean = false, modifier: Modifier = Modifier) {
    Button(modifier = modifier) { colors ->
        BasicButtonContent(
            text = text,
            loading = loading,
            fontSize = 14.sp,
            colors = colors,
        )
    }
}