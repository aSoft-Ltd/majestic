package majestic.multiselect

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * Default values for the dropdown menu of the [MultiSelect] component.
 *
 * @param containerColor The background color of the dropdown menu.
 * @param shape The shape of the dropdown menu.
 * @param shadowElevation The elevation of the shadow cast by the dropdown menu.
 * @param border The border width of the dropdown menu.
 * @param tonalElevation The tonal elevation of the dropdown menu.
 */

class DropDownDefaults(
    val containerColor: Color,
    val shape: Shape,
    val shadowElevation: Dp,
    val border: BorderStroke?,
    val tonalElevation: Dp
)