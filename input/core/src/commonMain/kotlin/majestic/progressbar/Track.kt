package majestic.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Track(
    items: Int,
    brush: (from: Int) -> Brush,
    modifier: Modifier = Modifier,
    lineWidth: Dp = 3.dp,
) = Row(
    modifier,
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
) {
    repeat(items - 1) { index ->
        LineTrack(
            modifier = Modifier.weight(1F),
            lineWidth = lineWidth,
            brush = brush.invoke(index)
        )
    }
}
