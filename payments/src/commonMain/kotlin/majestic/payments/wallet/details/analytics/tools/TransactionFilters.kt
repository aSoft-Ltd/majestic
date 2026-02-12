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
internal fun TransactionFilters(
    filter: TransactionFilter,
    color: Color,
    orientation: ScreenOrientation,
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
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .onClick { onChange(TransactionFilter.SPLINT) }
    ) {
        Text(text = "Split", fontSize = 12.sp, lineHeight = 1.sp, color = color)
    }
    Button(
        modifier = Modifier.clip(CircleShape)
            .background(if (filter == TransactionFilter.COMBINE) activeBg else defaultBg)
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .onClick { onChange(TransactionFilter.COMBINE) }
    ) {
        Text(text = "Combine", fontSize = 12.sp, lineHeight = 1.sp, color = color)
    }
}
