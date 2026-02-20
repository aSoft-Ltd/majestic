package majestic.shared.profiles.roles.item

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.shared.profiles.roles.data.Role
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource


data class RoleItemColors(
    val background: StateColors,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color,
    val trail: Color
)

internal fun Modifier.toRoleItem(
    interaction: MutableInteractionSource,
    hovered: Boolean,
    orientation: ScreenOrientation,
    colors: RoleItemColors
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = if (hovered) colors.background.focused else colors.background.unfocused,
        shape = RoundedCornerShape(10.dp)
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun RoleItem(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors,
    orientation: ScreenOrientation,
    onClick: () -> Unit
) = Row(
    modifier = modifier
        .pointerHoverIcon(PointerIcon.Hand)
        .onClick { onClick() },
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    RoleItemMain(
        modifier = Modifier.wrapContentSize(),
        index = index,
        role = role,
        colors = colors,
        orientation = orientation
    )

    Icon(
        imageVector = vectorResource(Res.drawable.ic_arrow_right),
        contentDescription = null,
        tint = colors.trail,
        modifier = Modifier.size(14.dp)
    )
}

@Composable
private fun RoleItemMain(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(role.resource),
        contentDescription = null,
        tint = colors.icon.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(colors.icon.background)
            .padding(8.dp)
            .size(24.dp)
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${index + 1}. ${role.title}",
            color = colors.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Text(
            text = role.description,
            color = colors.subtitle,
            fontSize = 13.sp,
            maxLines = if (orientation is Landscape) 2 else 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
