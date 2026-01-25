package majestic.shared.tools.filters

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
internal fun FilterButton(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black.copy(alpha = 0.8f)
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = label, fontSize = 12.sp, color = color, lineHeight = 1.sp)
    }
}