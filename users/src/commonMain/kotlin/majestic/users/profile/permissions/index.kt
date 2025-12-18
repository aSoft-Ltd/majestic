package majestic.users.profile.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import composex.screen.orientation.ScreenOrientation
import majestic.users.profile.permissions.detail.DetailedColors
import majestic.users.profile.permissions.detail.Details
import majestic.users.profile.permissions.detail.Permissions
import majestic.users.profile.permissions.detail.toDetailProperties
import majestic.users.tools.data.Permissions
import org.jetbrains.compose.resources.DrawableResource

data class PermissionsColors(
    val permission: PermissionColors,
    val detailed: DetailedColors
)

data class PermissionsProps(
    val permissions: List<Permissions>,
    val leadIcon: DrawableResource,
    val trailIcon: DrawableResource,
    val rightAngle: DrawableResource,
    val colors: PermissionsColors
)

internal fun Modifier.generalStyles(orientation: ScreenOrientation, props: PermissionsProps) = this
    .clip(RoundedCornerShape(if (orientation is Landscape) 20.dp else 0.dp))
    .fillMaxWidth()
    .then(if (orientation is Landscape) Modifier.wrapContentHeight() else Modifier.fillMaxHeight())
    .background(
        color = if (orientation is Landscape) props.colors.permission.background.copy(.5f) else Color.Transparent,
        shape = RoundedCornerShape(20.dp)
    )

@Composable
fun GeneralPermissions(
    orientation: ScreenOrientation,
    props: PermissionsProps,
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
                .generalStyles(orientation, props)
                .verticalScroll(rememberScrollState()),
            props = props,
            current = current,
            orientation = orientation
        )

        else -> current.activeObj?.let { activePermission ->
            Details(
                modifier = Modifier.generalStyles(orientation, props),
                current = current,
                orientation = orientation,
                props = props.toDetailProperties(activePermission)
            )
        }
    }
}