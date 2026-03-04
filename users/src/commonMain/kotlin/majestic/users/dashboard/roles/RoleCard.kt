package majestic.users.dashboard.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.tools.menu.OptionMenu
import majestic.shared.users.dashboard.RoleCardColors
import majestic.users.dashboard.roles.tools.CardHeader
import majestic.users.dashboard.roles.tools.Footer
import majestic.users.dashboard.roles.tools.RoleAction
import majestic.users.dashboard.roles.tools.Stat

internal data class RoleCardProps(
    val colors: RoleCardColors,
    val data: RoleCardData
)

internal data class RoleCardData(
    val actions: List<OptionMenu<RoleAction>>,
    val title: String,
    val body: String,
    val stats: List<Stat>
)

@Composable
internal fun RoleCard(
    modifier: Modifier = Modifier,
    props: RoleCardProps,
    orientation: ScreenOrientation,
) = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier
) {
    CardHeader(
        title = "Subject",
        modifier = Modifier.fillMaxWidth(),
        actions = props.data.actions,
        colors = props.colors.head,
        orientation = orientation,
    )

    Text(
        text = props.data.body,
        color = props.colors.body,
        fontSize = if (orientation is Portrait) 13.sp else 15.sp,
    )
    Footer(
        stats = props.data.stats,
        colors = props.colors.footer,
        modifier = Modifier.wrapContentSize(),
        orientation = orientation,
    )
}