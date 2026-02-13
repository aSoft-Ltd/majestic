package majestic.shared.credit.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_credict_card_accept
import org.jetbrains.compose.resources.painterResource

data class CreditTableWeights(
    val recipient: Float = 2.0f,
    val payer: Float = 2.0f,
    val ref: Float = 1.2f,
    val purchased: Float = 2.0f,
    val amount: Float = 0.8f,
    val actions: Float = 0.4f
)

@Composable
fun CreditTableHeader(
    colors: CreditTableColors,
    weights: CreditTableWeights,
    modifier: Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.weight(weights.recipient),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_credict_card_accept),
            contentDescription = null,
            tint = colors.surfaceColor.foreground.copy(alpha = .7f),
            modifier = Modifier.padding(end = 12.dp)
                .size(42.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.surfaceColor.background.copy(alpha = .4f))
                .padding(10.dp)
        )
        Text(
            text = "Recipient",
            color = colors.surfaceColor.foreground.copy(alpha = 0.6f),
            fontSize = 14.sp,
        )
    }
    Row(
        modifier = Modifier.weight(weights.payer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Payer",
            color = colors.surfaceColor.foreground.copy(alpha = 0.6f),
            fontSize = 14.sp,
        )
    }
    HeaderCell(text = "Reference", weight = weights.ref, colors = colors)
    HeaderCell(text = "Purchased", weight = weights.purchased, colors = colors)
    HeaderCell(text = "Amount", weight = weights.amount, colors = colors)

    Box(modifier = Modifier.weight(weights.actions))
}