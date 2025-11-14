package majestic.users.table.header.tools

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import majestic.tooling.onClick

data class EmailLabels(
    val email: String,
    val filters: FiltersLabels
)

data class EmailCellProperties(
    val colors: HeaderInnerColors,
    val labels: EmailLabels,
    val showFilters: Boolean,
)

@Composable
internal fun Email(
    props: EmailCellProperties,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val popColors = props.colors.compPopColors
        val color =
            if (expanded || isHovered) popColors.foreground else popColors.foreground.copy(0.6f)
        val decoration =
            if (expanded || isHovered) TextDecoration.Underline else TextDecoration.None

        Text(
            modifier = Modifier
                .hoverable(interactionSource = interactionSource)
                .onClick { expanded = !expanded },
            text = props.labels.email,
            textDecoration = if (props.showFilters) decoration else TextDecoration.None,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
