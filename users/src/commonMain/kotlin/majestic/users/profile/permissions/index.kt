package majestic.users.profile.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import majestic.users.tools.data.Permissions
import org.jetbrains.compose.resources.DrawableResource

data class PermissionsProps(
    val permissions: List<Permissions>,
    val trailIcon: DrawableResource,
    val colors: PermissionColors
)

@Composable
fun Permissions(
    orientation: ScreenOrientation,
    props: PermissionsProps,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val current = rememberPermissionScreenState()
    when (current.view) {
        Main -> {
            props.permissions.forEachIndexed { index, item ->
                Permission(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .onClick {
                            current.set(item)
                            current.detailed()
                        }
                        .padding(if (orientation is Landscape) 20.dp else 10.dp),
                    props = PermissionProperties(
                        colors = props.colors,
                        item = PermissionData(
                            permission = item,
                            trailIcon = props.trailIcon
                        ),
                    )
                )
                if (index != props.permissions.lastIndex) Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(props.colors.separator.copy(.05f))
                )
            }
        }

        else -> {
            Text(
                modifier = Modifier
                    .onClick {
                        current.main()
                        current.reset()
                    },
                text = "Here is where it all should be", color = props.colors.separator.copy(.5f)
            )
        }
    }
}

