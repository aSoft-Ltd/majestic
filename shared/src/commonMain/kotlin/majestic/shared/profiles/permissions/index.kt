package majestic.shared.profiles.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.Permission
import majestic.shared.profiles.permissions.detail.DetailColors
import majestic.shared.profiles.permissions.detail.Details
import majestic.shared.profiles.permissions.detail.Permissions
import majestic.shared.profiles.permissions.detail.PermissionsColors
import majestic.shared.profiles.permissions.detail.toDetailProperties
import majestic.shared.users.label.contacts.PermissionLabels

internal fun Modifier.generalStyles(orientation: ScreenOrientation, color: Color) =
    when (orientation) {
        is Landscape -> this
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = color.copy(.5f),
                shape = RoundedCornerShape(20.dp)
            )

        is Portrait -> this.fillMaxSize()
    }

data class GeneralPermissionColors(
    val whiteBackground: Color,
    val clientBackground: Color,
    val permission: PermissionColors,
    val detail: DetailColors
)

@Composable
fun GeneralPermissions(
    orientation: ScreenOrientation,
    permissions: List<Permission>,
    colors: GeneralPermissionColors,
    labels: PermissionLabels,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val current = rememberPermissionScreenState()
    when (current.view) {
        Main -> Permissions(
            modifier = Modifier
                .generalStyles(orientation = orientation, color = colors.clientBackground)
                .verticalScroll(rememberScrollState()),
            current = current,
            orientation = orientation,
            permissions = permissions,
            colors = PermissionsColors(
                background = colors.whiteBackground,
                permission = colors.permission
            )
        )

        Detailed -> current.activeObj?.let { activePermissions ->
            Details(
                modifier = Modifier.generalStyles(
                    orientation = orientation,
                    color = colors.clientBackground
                ),
                current = current,
                orientation = orientation,
                props = activePermissions.toDetailProperties(),
                labels = labels,
                colors = colors.detail
            )
        }
    }
}