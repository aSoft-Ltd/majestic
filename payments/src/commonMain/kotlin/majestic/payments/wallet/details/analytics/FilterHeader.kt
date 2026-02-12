package majestic.payments.wallet.details.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.Button
import majestic.payments.wallet.details.analytics.tools.AnalyticsFilter
import majestic.payments.wallet.details.analytics.tools.AnalyticsFilters

@Composable
internal fun FilterHeader(
    colors: ColorPair,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    var analyticsFilter by remember { mutableStateOf(AnalyticsFilter.ACCOUNT) }
    AnalyticsFilters(
        filter = analyticsFilter,
        color = colors.foreground,
        orientation = orientation,
        onChange = { analyticsFilter = it },
    )
    Button(
        modifier = Modifier.clip(CircleShape)
            .background(colors.foreground.copy(0.05f))
            .padding(horizontal = 10.dp)
    ) {
        Text(text = "Start Date", fontSize = 14.sp, lineHeight = 1.sp, color = colors.foreground.copy(0.7f))
        Spacer(Modifier.width(10.dp))
        Text(text = "-", fontSize = 30.sp, lineHeight = 1.sp, color = colors.foreground.copy(0.7f))
        Spacer(Modifier.width(10.dp))
        Text(text = "End Date", fontSize = 14.sp, lineHeight = 1.sp, color = colors.foreground.copy(0.7f))
    }
}
