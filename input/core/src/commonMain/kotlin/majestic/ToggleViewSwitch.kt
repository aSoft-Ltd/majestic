package majestic

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private fun Modifier.icon(iconInteraction: MutableInteractionSource) = this
    .size(18.dp)
    .pointerHoverIcon(PointerIcon.Hand)
    .hoverable(interactionSource = iconInteraction)

enum class ViewSwitch { LIST, GRID }

data class ViewIcon(
    val list: DrawableResource,
    val grid: DrawableResource
)

@Composable
fun ToggleViewSwitch(
    color: Color,
    icon: ViewIcon,
    view: ViewSwitch,
    orientation: ScreenOrientation,
    onSwitch: (ViewSwitch) -> Unit,
    modifier: Modifier = Modifier
) = Row(modifier = modifier) {
    // Portrait View
    val iconInteraction = remember { MutableInteractionSource() }
    if (orientation is Portrait) Icon(
        modifier = Modifier.icon(iconInteraction = iconInteraction)
            .onClick {
                val selectedView = when (view) {
                    ViewSwitch.LIST -> ViewSwitch.GRID
                    ViewSwitch.GRID -> ViewSwitch.LIST
                }
                onSwitch(selectedView)
            },
        painter = when (view) {
            ViewSwitch.LIST -> painterResource(icon.list)
            ViewSwitch.GRID -> painterResource(icon.grid)
        },
        contentDescription = "List or Grid View",
        tint = color
    )

    // Landscape View
    if (orientation is Landscape) Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val listInteraction = remember { MutableInteractionSource() }
        val listHovered by listInteraction.collectIsHoveredAsState()
        Icon(
            modifier = Modifier.icon(iconInteraction = listInteraction)
                .onClick { onSwitch(ViewSwitch.LIST) },
            painter = painterResource(icon.list),
            contentDescription = "List View",
            tint = if (listHovered || view == ViewSwitch.LIST) color else color.copy(0.5f)
        )

        VerticalDivider(thickness = 1.dp, color = color.copy(0.2f), modifier = Modifier.height(24.dp))

        val gridInteraction = remember { MutableInteractionSource() }
        val gridHovered by gridInteraction.collectIsHoveredAsState()
        Icon(
            modifier = Modifier.icon(iconInteraction = gridInteraction)
                .onClick { onSwitch(ViewSwitch.GRID) },
            painter = painterResource(icon.grid),
            contentDescription = "List Grid",
            tint = if (gridHovered || view == ViewSwitch.GRID) color else color.copy(0.5f)
        )
    }
}
