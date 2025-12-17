package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import majestic.users.profile.permissions.PermissionData
import majestic.users.profile.permissions.PermissionProperties
import majestic.users.profile.permissions.PermissionScreen
import majestic.users.profile.permissions.PermissionsProps


@Composable
internal fun Permissions(
    modifier: Modifier,
    props: PermissionsProps,
    current: PermissionScreen,
    orientation: ScreenOrientation
) = Column(modifier = modifier, verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
    props.permissions.forEachIndexed { index, item ->
        val interaction = MutableInteractionSource()
        val hovered by interaction.collectIsHoveredAsState()
        Permission(
            modifier = Modifier
                .hoverable(interaction)
                .fillMaxSize()
                .pointerHoverIcon(PointerIcon.Hand)
                .background(color = if (hovered) props.colors.permission.separator.copy(.05f) else Color.Transparent)
                .onClick {
                    current.set(item)
                    current.detailed()
                }
                .padding(if (orientation is Landscape) 20.dp else 10.dp),
            props = PermissionProperties(
                colors = props.colors.permission,
                item = PermissionData(
                    permission = item,
                    trailIcon = props.trailIcon
                ),
            )
        )
        if (index != props.permissions.lastIndex) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(props.colors.permission.separator.copy(.05f), 1.dp)
        )
    }
}