package majestic.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LineTrack(
    brush: Brush,
    modifier: Modifier = Modifier,
    lineWidth: Dp = 4.dp
) {
    Box(modifier = modifier) {
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(brush = brush),
            color = Color.Transparent,
            thickness = lineWidth
        )
    }
}
