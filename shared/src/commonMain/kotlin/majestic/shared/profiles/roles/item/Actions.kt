package majestic.shared.profiles.roles.item

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.menu.MenuOption
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.RoleItemColors
import majestic.shared.profiles.roles.data.RoleOption
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource


@Composable
internal fun Actions(
    modifier: Modifier,
    orientation: ScreenOrientation,
    onAdd: () -> Unit,
    colors: RoleItemColors,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(24.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    val addInteraction = remember { MutableInteractionSource() }
    val isAddHovered by addInteraction.collectIsHoveredAsState()
    if (orientation is Landscape) Icon(
        modifier = Modifier
            .onClick { onAdd() }
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(addInteraction)
            .background(
                if (isAddHovered) colors.add.background else Color.Transparent,
                shape = CircleShape
            )
            .padding(8.dp),
        painter = painterResource(Res.drawable.ic_add),
        contentDescription = "Add",
        tint = colors.add.foreground
    )
    MenuOption(
        colors = colors.options,
        orientation = orientation,
        actions = actions,
        onAction = onOption
    )
}

