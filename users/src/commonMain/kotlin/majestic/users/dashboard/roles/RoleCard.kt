package majestic.users.dashboard.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.roles.tools.CardHeader
import majestic.users.dashboard.roles.tools.CardStatColors
import majestic.users.dashboard.roles.tools.Footer
import majestic.users.dashboard.roles.tools.RoleAction
import majestic.users.dashboard.roles.tools.RoleHeaderColors
import majestic.users.dashboard.roles.tools.Stat
import menu.OptionMenu

internal data class RoleCardProps(val colors: RoleCardColors, val data: RoleCardData)
data class RoleCardColors(
    val head: RoleHeaderColors,
    val body: Color,
    val footer: CardStatColors
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
    orientation: ScreenOrientation
) = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier
) {
    CardHeader(
        title = "Subject",
        modifier = Modifier.fillMaxWidth(),
        actions = props.data.actions,
        colors = props.colors.head,
        orientation = orientation
    )

    Text(
        text = props.data.body,
        color = props.colors.body,
        fontSize = 13.sp,
    )
    Footer(
        stats = props.data.stats,
        colors = props.colors.footer,
        modifier = Modifier.wrapContentSize(),
    )
}
