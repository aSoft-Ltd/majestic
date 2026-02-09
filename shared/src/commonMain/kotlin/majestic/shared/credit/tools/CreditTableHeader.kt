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
import majestic.ThemeColor
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
    theme: ThemeColor,
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
            tint = theme.surface.actual.color.copy(alpha = .7f),
            modifier = Modifier.padding(end = 12.dp)
                .size(42.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(theme.surface.contra.color.copy(alpha = .7f))
                .padding(10.dp)
        )
        Text(
            text = "Recipient",
            color = theme.surface.contra.color.copy(alpha = 0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
    Row(
        modifier = Modifier.weight(weights.payer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Payer",
            color = theme.surface.contra.color.copy(alpha = 0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
    HeaderCell(text = "Reference", weight = weights.ref, theme = theme)
    HeaderCell(text = "Purchased", weight = weights.purchased, theme = theme)
    HeaderCell(text = "Amount", weight = weights.amount, theme = theme)

    Box(modifier = Modifier.weight(weights.actions))
}