package majestic.shared.credit.tools.cells

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.credit.CreditUsage
import majestic.shared.credit.tools.CreditTableColors

@Composable
fun CreditPurchasedCell(
    credit: CreditUsage,
    colors: CreditTableColors,
    modifier: Modifier = Modifier
) = Row(modifier = modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(credit.itemColor.copy(alpha = 0.2f))
        ) {
            Text(
                text = credit.itemName.first().toString(),
                color = credit.itemColor,
                fontSize = 12.sp,
                lineHeight = 1.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .size(12.dp)
                .clip(CircleShape)
                .background(credit.itemColor)
        )
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = credit.itemName,
                color = credit.itemColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Box(Modifier.weight(1f))
            Text(
                text = "${credit.used}/${credit.total} • ${credit.date}",
                color = colors.surfaceColor.foreground.copy(alpha = 0.5f),
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        var targetProgress by remember { mutableFloatStateOf(0f) }
        LaunchedEffect(Unit) { targetProgress = credit.used.toFloat() / credit.total.toFloat() }
        val animatedProgress by animateFloatAsState(
            targetValue = targetProgress,
            animationSpec = tween(durationMillis = 1000, delayMillis = 100, easing = FastOutSlowInEasing),
            label = "Progress Animation"
        )
        Box(
            modifier = Modifier
                .padding(top = 13.dp)
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(100))
                .background(credit.itemColor.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(100))
                    .background(credit.itemColor)
            )
        }
    }
}