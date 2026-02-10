package majestic.shared.profiles.permissions.detail

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
import majestic.icons.Res
import majestic.icons.ic_arrow_right
import majestic.shared.profiles.Permission
import majestic.shared.profiles.permissions.Permission
import majestic.shared.profiles.permissions.PermissionItemColors
import majestic.shared.profiles.permissions.PermissionData
import majestic.shared.profiles.permissions.PermissionScreen
import majestic.tooling.onClick

internal fun Modifier.permissionItem(
    hovered: Boolean,
    permissions: List<Permission>,
    hoverColor: Color,
    index: Int,
    current: PermissionScreen,
    item: Permission,
    interaction: MutableInteractionSource,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .wrapContentHeight()
    .pointerHoverIcon(PointerIcon.Hand)
    .background(
        color = if (hovered) hoverColor.copy(.05f) else Color.Transparent,
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
    val hover: Color,
    val permission: PermissionItemColors
)

@Composable
internal fun Permissions(
    modifier: Modifier,
    permissions: List<Permission>,
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
                hoverColor = colors.hover
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
                .underline(colors.hover.copy(.05f), 1.dp)
        )
    }
}