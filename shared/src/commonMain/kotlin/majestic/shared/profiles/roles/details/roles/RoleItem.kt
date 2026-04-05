package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.icons.ic_more_horizontal
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.details.roles.tools.actions
import majestic.shared.tools.dropdown.toDropdownItems
import majestic.shared.tools.rememberHoverBackground
import org.jetbrains.compose.resources.vectorResource

data class RoleItemColors(
    val background: Color,
    val foreground: Color,
    val icon: ColorPair,
    val title: StateColors,
    val subtitle: Color,
    val trail: Color,
    val dropdown: DropdownColors
)

@Composable
internal fun Modifier.roleItem(
    index: Int,
    roles: List<Role>,
    orientation: ScreenOrientation,
    colors: RoleItemColors
): Modifier {
    val (background, interaction) = rememberHoverBackground(colors.background, colors.foreground)

    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = if (index == roles.lastIndex && orientation is Landscape) 10.dp else 0.dp,
        bottomEnd = if (index == roles.lastIndex && orientation is Landscape) 10.dp else 0.dp
    )

    return this
        .pointerHoverIcon(PointerIcon.Hand)
        .fillMaxWidth()
        .hoverable(interaction)
        .clip(shape)
        .background(background)
        .padding(if (orientation is Landscape) 20.dp else 10.dp)
}

@Composable
internal fun RoleItem(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors,
    labels: RoleActionLabels,
    orientation: ScreenOrientation,
    onView: () -> Unit,
    onUnassign: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    RoleItemMain(
        modifier = Modifier.weight(.9f),
        index = index,
        role = role,
        colors = colors
    )

    when (orientation) {
        is Landscape -> Icon(
            imageVector = vectorResource(Res.drawable.ic_arrow_right),
            contentDescription = null,
            tint = colors.trail,
            modifier = Modifier.size(14.dp)
        )

        is Portrait -> Dropdown(
            items = labels.actions().toDropdownItems(),
            onAction = { action ->
                when (action) {
                    RoleAction.View -> onView()
                    RoleAction.Unassign -> onUnassign()
                }
            },
            colors = colors.dropdown,
            icon = vectorResource(Res.drawable.ic_more_horizontal),
            rotationTarget = 90f,
            isListItem = true
        )
    }
}