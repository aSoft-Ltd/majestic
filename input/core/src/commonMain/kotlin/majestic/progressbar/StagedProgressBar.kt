package majestic.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StagedProgressBar(
    stages: List<Stage>,
    current: Int = 0,
    stroke: Dp = 4.dp,
    colors: ProgressBarColors = ProgressBarColors.Default,
    modifier: Modifier = Modifier
) = Box(modifier = modifier, contentAlignment = Alignment.Center) {
    Track(
        lineWidth = stroke,
        items = stages.size,
        brush = { index ->
            if ((current - index) > 1) {
                Brush.horizontalGradient(
                    listOf(colors.complete, colors.complete)
                )
            } else if (current <= index) {
                Brush.horizontalGradient(
                    listOf(
                        Color.Black.copy(alpha = 0.1f),
                        Color.Black.copy(alpha = 0.1f)
                    )
                )
            } else {
                Brush.horizontalGradient(
                    listOf(
                        colors.current.copy(alpha = 0.0f),
                        colors.current.copy(alpha = 0.4f),
                        colors.current
                    )
                )
            }
        }
    )
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for ((index, stage) in stages.withIndex()) Box(contentAlignment = Alignment.Center) {
            stage.content(
                when {
                    index < current -> Stage.Status.Complete
                    index == current -> Stage.Status.Current
                    else -> Stage.Status.Pending
                }
            )
        }
    }
}
