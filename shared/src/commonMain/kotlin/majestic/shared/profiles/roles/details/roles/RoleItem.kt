package majestic.shared.profiles.roles.details.roles

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.icons.ic_more_vertical
import majestic.shared.tools.menu.MenuOption
import majestic.shared.tools.menu.MenuOptionColors
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.details.roles.tools.actions
import org.jetbrains.compose.resources.vectorResource


data class RoleItemColors(
    val background: StateColors,
    val icon: ColorPair,
    val title: StateColors,
    val subtitle: Color,
    val trail: Color,
    val action: MenuOptionColors
)

@Composable
internal fun Modifier.roleItem(
    interaction: MutableInteractionSource = remember { MutableInteractionSource() },
    index: Int,
    roles: List<Role>,
    orientation: ScreenOrientation,
    colors: RoleItemColors
): Modifier {
    val hovered by interaction.collectIsHoveredAsState()

    val animatedAlpha by animateFloatAsState(
        targetValue = if (hovered) 1f else 0f,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label = "hoverOverlayAlpha"
    )

    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = if (index == roles.lastIndex) 10.dp else 0.dp,
        bottomEnd = if (index == roles.lastIndex) 10.dp else 0.dp
    )

    return this
        .fillMaxWidth()
        .hoverable(interaction)
        .background(color = colors.background.unfocused, shape = shape)
        .background(
            color = colors.background.focused.copy(
                alpha = colors.background.focused.alpha * animatedAlpha
            ),
            shape = shape
        )
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

        is Portrait -> MenuOption(
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
                    RoleAction.View -> onView()
                    RoleAction.Unassign -> onUnassign()
                }
            },
        )
    }
}