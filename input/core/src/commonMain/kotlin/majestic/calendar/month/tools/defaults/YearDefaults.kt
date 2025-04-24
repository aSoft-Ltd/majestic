package majestic.calendar.month.tools.defaults

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class YearDefaults(
    val fontSize: TextUnit,
    val colors: YearDefaultColors
) {
    companion object {
        val Default = YearDefaults(
            fontSize = 16.sp,
            colors = YearDefaultColors.Default
        )
    }
}