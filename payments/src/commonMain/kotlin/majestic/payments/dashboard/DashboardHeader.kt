package majestic.payments.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.payments.labels.DashboardHeaderLabels

@Composable
fun DashboardHeader(
    labels: DashboardHeaderLabels,
    colors: ColorPair,
    modifier: Modifier = Modifier,
    trailing: @Composable (() -> Unit)? = null
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    Column {
        Text(
            text = labels.section.label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colors.foreground,
        )
        Text(
            text = labels.section.description,
            fontSize = 14.sp,
            color = colors.foreground.copy(0.3f),
        )
    }
    trailing?.invoke()
}
