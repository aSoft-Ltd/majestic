package majestic.payments.dashboard.wallet.chart.tools

import androidx.compose.ui.graphics.Color

data class BarData(
    val label: String,
    val value: Int,
    val percent: Float,
    val color: Color = Color.Blue
) {
    companion object {
        fun normalizeTo100(value: Double, max: Double): Float {
            if (max == value) return 100f
            val t = (value / max).coerceIn(0.0, 1.0)
            return (1 + t * 99).toFloat()
        }
    }
}
