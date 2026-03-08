package majestic.payments.wallet.details.analytics.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.button.Button
import majestic.button.basic.BasicButtonContent
import majestic.icons.Res
import majestic.icons.ic_chart_bar_line_solid
import majestic.icons.ic_chart_column
import majestic.payments.labels.wallet.AnalyticsLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun TransactionFilters(
    labels: AnalyticsLabels,
    filter: TransactionFilter,
    color: Color,
    onChange: (TransactionFilter) -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val defaultBg = color.copy(0.03f)
    val activeBg = color.copy(0.07f)

    Button(
        modifier = Modifier.clip(CircleShape)
            .background(if (filter == TransactionFilter.SPLINT) activeBg else defaultBg)
            .padding(horizontal = 10.dp)
            .onClick { onChange(TransactionFilter.SPLINT) }
    ) { colors ->
        BasicButtonContent(
            leadingIcon = vectorResource(Res.drawable.ic_chart_column),
            text = labels.split,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            textAlpha = if (filter == TransactionFilter.SPLINT) 0.7f else 0.3f,
            leadingIconAlpha = if (filter == TransactionFilter.SPLINT) 0.7f else 0.3f,
            colors = colors
        )
    }
    Button(
        modifier = Modifier.clip(CircleShape)
            .background(if (filter == TransactionFilter.COMBINE) activeBg else defaultBg)
            .padding(horizontal = 10.dp)
            .onClick { onChange(TransactionFilter.COMBINE) }
    ) { colors ->
        BasicButtonContent(
            leadingIcon = vectorResource(Res.drawable.ic_chart_bar_line_solid),
            text = labels.combine,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            textAlpha = if (filter == TransactionFilter.COMBINE) 0.7f else 0.3f,
            leadingIconAlpha = if (filter == TransactionFilter.COMBINE) 0.7f else 0.3f,
            colors = colors
        )
    }
}
