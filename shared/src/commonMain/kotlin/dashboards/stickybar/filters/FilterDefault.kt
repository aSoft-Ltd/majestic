package dashboards.stickybar.filters

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.ColorPair

data class FilterDefault(
    val drawerColor: Color,
    val containerShape: Shape,
    val drawerShape: Shape,
    val colors: SelectFilterColors = SelectFilterColors.Default,
    val item: ColorPair
) {
    companion object {
        val Defaults = FilterDefault(
            drawerColor = Color.Transparent,
            containerShape = RoundedCornerShape(12.dp),
            drawerShape = RoundedCornerShape(12.dp),
            colors = SelectFilterColors.Default,
            item = ColorPair(
                background = Color.Transparent,
                foreground = Color.Transparent
            )
        )
    }
}
