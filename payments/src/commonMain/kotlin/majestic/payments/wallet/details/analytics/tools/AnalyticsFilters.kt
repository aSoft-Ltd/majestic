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
import majestic.icons.ic_apple_reminder
import majestic.icons.ic_book_04
import majestic.payments.labels.wallet.AnalyticsLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun AnalyticsFilters(
    labels: AnalyticsLabels,
    filter: AnalyticsFilter,
    color: Color,
    onChange: (AnalyticsFilter) -> Unit,
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
            .background(if (filter == AnalyticsFilter.ACCOUNT) activeBg else defaultBg)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .onClick { onChange(AnalyticsFilter.ACCOUNT) }
    ) { colors ->
        BasicButtonContent(
            leadingIcon = vectorResource(Res.drawable.ic_apple_reminder),
            text = labels.btnPerAccount,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlpha = if (filter == AnalyticsFilter.ACCOUNT) 0.7f else 0.3f,
            leadingIconAlpha = if (filter == AnalyticsFilter.ACCOUNT) 0.7f else 0.3f,
            colors = colors
        )
    }
    Button(
        modifier = Modifier.clip(CircleShape)
            .background(if (filter == AnalyticsFilter.ITEM) activeBg else defaultBg)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .onClick { onChange(AnalyticsFilter.ITEM) }
    ) { colors ->
        BasicButtonContent(
            leadingIcon = vectorResource(Res.drawable.ic_book_04),
            text = labels.btnPerItem,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlpha = if (filter == AnalyticsFilter.ITEM) 0.7f else 0.3f,
            leadingIconAlpha = if (filter == AnalyticsFilter.ITEM) 0.7f else 0.3f,
            colors = colors
        )
    }
}
