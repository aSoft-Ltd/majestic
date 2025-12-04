package majestic.payments.dashboard.wallet.chart.tools

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface BarShape

data class RoundedRectangleShape(
    val topStart: Dp = 0.dp,
    val topEnd: Dp = 0.dp,
    val bottomStart: Dp = 0.dp,
    val bottomEnd: Dp = 0.dp
) : BarShape

object Circle : BarShape
object Diamond : BarShape
object Rectangle : BarShape
