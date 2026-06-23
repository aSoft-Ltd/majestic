package majestic.shared.credit.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_credict_card_accept
import majestic.shared.credit.CreditUsage
import majestic.tooling.separator
import org.jetbrains.compose.resources.painterResource
import symphony.columns.Column

data class CreditTableWeights(
    val recipient: Float = 2.0f,
    val payer: Float = 2.0f,
    val ref: Float = 1.2f,
    val purchased: Float = 2.6f,
    val amount: Float = 1.0f,
    val actions: Float = 0.5f,
)

@Composable
fun RowScope.CreditTableHeader(
    colors: CreditTableColors,
    column: Column<CreditUsage>,
    weights: Map<Column<CreditUsage>, Float>,
    cellHeight: Dp = 70.dp,
) = when (column.index) {
    0 -> {
        Row(
            modifier = Modifier
                .weight(weights.getValue(column))
                .height(cellHeight)
                .background(colors.header)
                .separator(colors.body)
                .padding(horizontal = 12.dp),
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    weights.size - 1 -> Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .weight(weights.getValue(column))
            .height(cellHeight)
            .background(colors.header)
            .separator(colors.separator)
            .padding(horizontal = 12.dp),
    ) {}

    else -> Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .weight(weights.getValue(column))
            .height(cellHeight)
            .background(colors.header)
            .separator(colors.separator)
            .padding(horizontal = 12.dp),
    ) {
        Text(
            text = column.name,
            color = colors.surfaceColor.foreground.copy(0.5f),
            maxLines = 1,
            lineHeight = 1.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}