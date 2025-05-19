package majestic.calendar.month.tools.defaults

import androidx.compose.ui.graphics.Color

data class MonthYearColors(
    val text: Color,
    val tint: TintColors
) {
    companion object {
        val Default = MonthYearColors(
            text = Color.White,
            tint = TintColors.Default
        )
    }
}