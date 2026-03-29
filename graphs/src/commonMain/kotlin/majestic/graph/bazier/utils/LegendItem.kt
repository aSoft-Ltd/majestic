package majestic.graph.bazier.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.bazier.LineSeriesColors

@Composable
internal fun LegendItem(
    label: String,
    colors: LineSeriesColors
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(8.dp)
            .background(color = colors.key, shape = CircleShape)
    )
    Text(
        text = label,
        fontSize = 11.sp,
        color = colors.label,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
