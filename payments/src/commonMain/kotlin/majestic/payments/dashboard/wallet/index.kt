package majestic.payments.dashboard.wallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.graph.BarGraph
import majestic.graph.tools.bar.BarShape
import majestic.payments.tools.formatWithCommas

@Composable
fun WalletBar(
    item: BarData,
    color: Color,
    shape: BarShape,
    animationDuration: Int = 2000,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    Text(
        text = item.label,
        fontSize = 12.sp,
        lineHeight = 8.sp,
        color = color.copy(0.5f)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BarGraph(
            value = item.percent,
            color = item.color,
            shape = shape,
            animationDuration = animationDuration,
            modifier = Modifier.weight(2.5f).height(15.dp),
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "${item.value.formatWithCommas()}/=",
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            color = color.copy(0.8f)
        )
    }
}
