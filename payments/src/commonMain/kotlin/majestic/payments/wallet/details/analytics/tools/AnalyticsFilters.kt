package majestic.payments.wallet.details.analytics.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.button.Button
import majestic.tooling.onClick

@Composable
internal fun AnalyticsFilters(
    filter: AnalyticsFilter,
    color: Color,
    orientation: ScreenOrientation,
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
            .padding(10.dp)
            .onClick { onChange(AnalyticsFilter.ACCOUNT) }
    ) {
        Text(text = "Per Account", fontSize = 14.sp, lineHeight = 1.sp, color = color)
    }
    Button(
        modifier = Modifier.clip(CircleShape)
            .background(if (filter == AnalyticsFilter.ITEM) activeBg else defaultBg)
            .padding(10.dp)
            .onClick { onChange(AnalyticsFilter.ITEM) }
    ) {
        Text(text = "Per Class", fontSize = 14.sp, lineHeight = 1.sp, color = color)
    }
}
