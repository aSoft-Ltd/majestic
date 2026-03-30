package majestic.button.expandable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import composex.screen.orientation.ScreenOrientation
import majestic.button.Button
import majestic.button.appearence.ExpandableButtonElement

@Composable
fun ExpandableButton(
    label: String,
    icon: ImageVector,
    orientation: ScreenOrientation,
    forceExpanded: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val buttonInteraction = modifier.source()
    val buttonHovered by buttonInteraction.collectIsHoveredAsState()

    Button(modifier = modifier) { colors ->
        ExpandableButtonContent(
            icon = icon,
            text = label,
            isHovered = buttonHovered,
            orientation = orientation,
            forceExpanded = forceExpanded,
            colors = colors,
        )
    }
}

@Composable
private fun Modifier.source(): MutableInteractionSource {
    var source: MutableInteractionSource? = null

    any { element ->
        (element as? ExpandableButtonElement)?.let { source = it.source } != null
    }

    return source ?: remember { MutableInteractionSource() }
}