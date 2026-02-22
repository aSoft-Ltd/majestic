package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.icons.ic_more_vertical
import majestic.shared.menu.MenuOption
import majestic.shared.menu.MenuOptionColors
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.details.roles.tools.actions
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource


data class RoleItemColors(
    val background: StateColors,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color,
    val trail: Color,
    val action: MenuOptionColors
)

internal fun Modifier.roleItem(
    interaction: MutableInteractionSource = remember { MutableInteractionSource() },
    hovered: Boolean,
    index: Int,
    roles: List<Role>,
    orientation: ScreenOrientation,
    colors: RoleItemColors
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = if (hovered) colors.background.focused else colors.background.unfocused,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = if (index == roles.lastIndex) 10.dp else 0.dp,
            bottomEnd = if (index == roles.lastIndex) 10.dp else 0.dp
        )
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun RoleItem(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors,
    labels: RoleActionLabels,
    orientation: ScreenOrientation,
    onClick: () -> Unit,
    onUnassign: () -> Unit
) = Row(
    modifier = modifier
        .pointerHoverIcon(PointerIcon.Hand)
        .onClick { onClick() },
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    RoleItemMain(
        modifier = Modifier.weight(.9f),
        index = index,
        role = role,
        colors = colors,
        orientation = orientation
    )

    if (orientation is Landscape) Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.trail,
        modifier = Modifier.size(14.dp)
    )
    else MenuOption(
        colors = colors.action,
        icon = {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_more_vertical),
                modifier = Modifier.size(20.dp),
                tint = colors.action.icon.foreground,
                contentDescription = null
            )
        },
        orientation = orientation,
        actions = labels.actions(),
        onAction = { action ->
            when (action) {
                RoleAction.View -> onClick()
                RoleAction.Unassign -> onUnassign()
            }
        },
    )
}


