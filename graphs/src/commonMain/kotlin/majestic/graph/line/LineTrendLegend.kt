package majestic.graph.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineTrendLegend(
    textColor: Color,
    isLandscape: Boolean,
    series: List<LineTrendSeries>,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(if (isLandscape) 22.dp else 16.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    series.forEach { s ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(s.color.copy(alpha = 0.95f))
            )
            Text(
                text = s.name,
                color = textColor,
                fontSize = 14.sp,
                maxLines = 1,
                lineHeight = 1.sp,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
