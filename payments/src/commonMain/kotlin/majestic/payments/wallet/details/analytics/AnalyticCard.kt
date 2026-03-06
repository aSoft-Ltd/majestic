package majestic.payments.wallet.details.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.tooling.separator

@Composable
internal fun AnalyticCard(
    title: String,
    colors: ColorPair,
    modifier: Modifier = Modifier,
    filters: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = colors.background)
            .heightIn(min = 40.dp)
            .separator(color = colors.foreground.copy(0.03f))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            lineHeight = 1.sp,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        filters()
    }
    content()
}
