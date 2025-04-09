package majestic.multiselect.defaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Default values for the selected item in the [MultiSelect] component.
 *
 * @param containerColor The background color of the selected item.
 * @param containerShape The shape of the selected item.
 * @param borderWidth The width of the border around the selected item.
 * @param borderColor The color of the border around the selected item.
 * @param borderShape The shape of the border around the selected item.
 */
data class SelectedDefaults(
    val containerColor: Color,
    val containerShape: Shape,
    val borderWidth: Dp,
    val borderColor: Color,
    val borderShape: Shape
) {
    companion object {
        val Default = SelectedDefaults(
            containerColor = Color.Transparent,
            containerShape = RoundedCornerShape(8.dp),
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            borderShape = RoundedCornerShape(8.dp)
        )
    }
}