package majestic.multiselect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * Default values for the selected item in the [MultiSelect] component.
 *
 * @param containerColor The background color of the selected item.
 * @param containerShape The shape of the selected item.
 * @param borderWidth The width of the border around the selected item.
 * @param borderColor The color of the border around the selected item.
 * @param borderShape The shape of the border around the selected item.
 */
class SelectedDefaults(
    val containerColor: Color,
    val containerShape: Shape,
    val borderWidth: Dp,
    val borderColor: Color,
    val borderShape: Shape,
)