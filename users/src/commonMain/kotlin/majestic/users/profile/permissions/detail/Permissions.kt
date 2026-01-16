package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import majestic.editor.toolbar.underline
import majestic.tooling.onClick
import majestic.users.profile.permissions.Permission
import majestic.users.profile.permissions.PermissionColors
import majestic.users.profile.permissions.PermissionData
import majestic.users.profile.permissions.PermissionScreen
import majestic.users.tools.data.Permissions
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_right

internal fun Modifier.permissionItem(
    hovered: Boolean,
    permissions: List<Permissions>,
    background: Color,
    index: Int,
    current: PermissionScreen,
    item: Permissions,
    interaction: MutableInteractionSource,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .wrapContentHeight()
    .pointerHoverIcon(PointerIcon.Hand)
    .background(
        color = if (hovered) background.copy(.05f) else Color.Transparent,
        shape = when (index) {
            0 -> RoundedCornerShape(
                topStart = if (orientation is Landscape) 20.dp else 0.dp,
                topEnd = if (orientation is Landscape) 20.dp else 0.dp
            )

            permissions.lastIndex -> RoundedCornerShape(
                bottomStart = if (orientation is Landscape) 20.dp else 0.dp,
                bottomEnd = if (orientation is Landscape) 20.dp else 0.dp
            )

            else -> RoundedCornerShape(0.dp)
        }
    )
    .onClick {
        current.set(item)
        current.detailed()
    }
    .hoverable(interaction)
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

data class PermissionsColors(
    val background: Color,
    val permission: PermissionColors
)

@Composable
internal fun Permissions(
    modifier: Modifier,
    permissions: List<Permissions>,
    current: PermissionScreen,
    colors: PermissionsColors,
    orientation: ScreenOrientation
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start
) {
    permissions.forEachIndexed { index, item ->
        val interaction = remember(item) { MutableInteractionSource() }
        val hovered by interaction.collectIsHoveredAsState()
        Permission(
            modifier = Modifier.permissionItem(
                hovered = hovered,
                permissions = permissions,
                index = index,
                current = current,
                item = item,
                interaction = interaction,
                orientation = orientation,
                background = colors.background
            ),
            item = PermissionData(
                permission = item,
                trailIcon = Res.drawable.ic_arrow_right
            ),

            colors = colors.permission,
            orientation = orientation
        )
        if (index != permissions.lastIndex) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.background.copy(.05f), 1.dp)
        )
    }
}