package majestic.payments.dashboard.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor

data class Series(
    val label: String,
    val color: Color
)

@Composable
fun GraphLegend(
    theme: ThemeColor,
    series: List<Series>,
    dotSize: Dp = 12.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        series.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(modifier = Modifier.size(dotSize).clip(CircleShape)) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawRect(color = item.color)
                    }
                }

                Text(
                    text = item.label,
                    fontSize = 12.sp,
                    color = theme.surface.contra.color
                )
            }
        }
    }
}
