package majestic.users.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.roles.HeaderColors
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.roles.Labels
import majestic.users.dashboard.roles.RoleCardColors
import majestic.users.dashboard.roles.UserBodyProps
import majestic.users.dashboard.roles.UserRoleBodyLabels
import majestic.users.dashboard.roles.UsersRoles
import majestic.users.dashboard.roles.UsersRolesProps
import majestic.users.dashboard.roles.tools.getRoles
import majestic.users.dashboard.table.UserTableProps
import majestic.users.dashboard.table.UsersTable
import majestic.users.dashboard.tools.portraitHeader.PortraitHeaderColors
import majestic.users.dashboard.tools.toColumnLabels
import majestic.users.labels.UsersLabels
import majestic.users.table.header.tools.getHeaderLabels
import majestic.users.table.tools.data.avatars
import majestic.users.table.tools.data.permissions
import majestic.users.table.tools.data.roles
import majestic.users.tools.data.HeaderIcons
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.getDashboardWeights
import nation.Country
import symphony.columnsOf
import symphony.linearPaginatorOf
import symphony.tableOf
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.allDrawableResources
import tz.co.asoft.majestic_users.generated.resources.ic_calendar
import tz.co.asoft.majestic_users.generated.resources.ic_clock_01
import tz.co.asoft.majestic_users.generated.resources.ic_laptop_phone
import tz.co.asoft.majestic_users.generated.resources.tz_flag

data class TableViewProps(
    val table: UserTableProps,
    val roles: UserRoleColors,
    val tabs: PortraitHeaderColors
)

data class UserRoleColors(
    val background: Color,
    val header: HeaderColors,
    val roleCard: RoleCardColors
)

@Composable
internal fun LandscapeView(
    orientation: ScreenOrientation,
    props: TableViewProps,
    labels: UsersLabels,
    onItemClick: () -> Unit = {},
    manage: () -> Unit = {},
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
    val paginator = remember { linearPaginatorOf<UsersData>(20) }
    val country = Country.entries.random()
    val key = remember(country) { "flag_" + country.code.lowercase() }
    val flag = remember(key) { Res.allDrawableResources[key] }

    val table = remember {
        tableOf(
            paginator = paginator,
            columns = columnsOf {
                column("checkbox", key = "checkbox") { it.item.selected }
                column(labels.table.head.name) { it.item.fullName }
                column(labels.table.head.email) { it.item.email }
                column(labels.table.head.id) { it.item.id }
                column(labels.table.head.dateJoined) { it.item.dateJoined }
                column(labels.table.head.lastActive) { it.item.lastActive }
                column(labels.table.head.status) { it.item.status }
                column("", "action") { }
            }
        )
    }

    var perPage by remember { mutableStateOf(20) }
    LaunchedEffect(perPage) {
        paginator.initialize {
            buildList {
                repeat(perPage) {
                    add(
                        UsersData.getUserData(
                            avatars(), permissions(), roles(),
                            headerIcons = HeaderIcons(
                                Res.drawable.ic_calendar,
                                Res.drawable.ic_laptop_phone,
                                Res.drawable.ic_clock_01,
                                flag = flag ?: Res.drawable.tz_flag
                            )
                        )
                    )
                }
            }
        }
    }

    val columns = table.columns.current.watchAsState()

    val weight = getDashboardWeights(
        columns = columns,
        labels = getHeaderLabels(labels.table.toColumnLabels())
    )

    UsersTable(
        modifier = (if (orientation == Landscape) Modifier
            .weight(1f)
            .fillMaxWidth()
            .fillMaxHeight()
        else Modifier
            .height(500.dp))
            .clip(RoundedCornerShape(20.dp)),
        table = table,
        orientation = orientation,
        props = props.table,
        onItemClick = onItemClick,
        weight = weight,
        add = { },
        manage = {}
    )
    UsersRoles(
        modifier = (if (orientation == Landscape) Modifier
            .weight(1f)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(props.roles.background, RoundedCornerShape(20.dp))
        else Modifier
            .height(500.dp))
            .clip(RoundedCornerShape(20.dp)),
        orientation = orientation,
        props = UsersRolesProps(
            header = HeaderProps(
                labels = Labels(
                    title = "Roles",
                    manage = "Manage"
                ),
                colors = props.roles.header
            ),
            body = UserBodyProps(
                colors = props.roles.roleCard,
                labels = UserRoleBodyLabels(
                    actions = labels.dashboard.roles.actions.getRoles(),
                    permission = "",
                    userAssigned = ""
                )
            )
        ),
        add = {},
        manage = manage,
    )
}