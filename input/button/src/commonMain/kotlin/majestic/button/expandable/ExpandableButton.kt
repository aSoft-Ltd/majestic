package majestic.button.expandable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.Button
import majestic.button.appearence.expandableButton

@Composable
fun ExpandableButton(
    label: String,
    icon: ImageVector,
    colors: ColorPair,
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    orientation: ScreenOrientation,
    forceExpanded: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val hovered by source.collectIsHoveredAsState()

    Button(
        modifier = modifier.expandableButton(
            colors = colors,
            source = source, onClick = onClick
        )
    ) { colors ->
        ExpandableButtonContent(
            icon = icon,
            text = label,
            isHovered = hovered,
            orientation = orientation,
            forceExpanded = forceExpanded,
            colors = colors,
        )
    }
}