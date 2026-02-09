package majestic.users.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.allDrawableResources
import majestic.icons.ic_calendar
import majestic.icons.ic_clock_01
import majestic.icons.ic_laptop_phone
import majestic.icons.tz_flag
import majestic.shared.menu.MenuOption
import majestic.shared.users.UsersLabels
import majestic.shared.users.dashboard.PortraitHeaderColors
import majestic.shared.users.dashboard.UserRoleColors
import majestic.shared.users.tools.HeaderIcons
import majestic.shared.users.tools.UsersData
import majestic.users.dashboard.roles.HeaderProps
import majestic.users.dashboard.roles.Labels
import majestic.users.dashboard.roles.UserBodyProps
import majestic.users.dashboard.roles.UserRoleBodyLabels
import majestic.users.dashboard.roles.UsersRoles
import majestic.users.dashboard.roles.UsersRolesProps
import majestic.users.dashboard.roles.tools.getRoles
import majestic.users.dashboard.table.UserTableProps
import majestic.users.dashboard.table.UsersTable
import majestic.users.dashboard.tools.toColumnLabels
import majestic.users.table.tools.data.avatars
import majestic.users.table.tools.data.getOptions
import majestic.users.table.tools.data.permissions
import majestic.users.table.tools.data.roles
import majestic.users.tools.data.getDashboardWeights
import nation.Country
import symphony.columnsOf
import symphony.linearPaginatorOf
import symphony.tableOf

data class TableViewProps(
    val table: UserTableProps,
    val roles: UserRoleColors,
    val tabs: PortraitHeaderColors
)

@Composable
internal fun LandscapeView(
    orientation: ScreenOrientation,
    props: TableViewProps,
    labels: UsersLabels,
    onItemClick: () -> Unit = {},
    manageUsers: () -> Unit = {},
    manageRoles: () -> Unit = {},
    addUser: () -> Unit = {},
    addRole: () -> Unit = {},
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
        labels = labels.table.toColumnLabels()
    )

    UsersTable(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .weight(1f)
            .fillMaxWidth()
            .background(props.table.body.colors.background, RoundedCornerShape(20.dp))
            .fillMaxHeight(),
        table = table,
        orientation = orientation,
        props = props.table,
        onItemClick = onItemClick,
        weight = weight,
        add = addUser,
        manage = manageUsers,
        labels = labels
    ) {
        MenuOption(
            orientation = orientation,
            actions = getOptions(labels.table),
            colors = props.table.body.colors.row.menuOption
        ) { action -> }
    }
    UsersRoles(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .weight(1f)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(props.roles.background, RoundedCornerShape(20.dp)),
        orientation = orientation,
        props = UsersRolesProps(
            header = HeaderProps(
                labels = Labels(
                    title = labels.dashboard.roles.title,
                    manage = labels.dashboard.roles.manage
                ),
                colors = props.roles.header
            ),
            body = UserBodyProps(
                colors = props.roles.roleCard,
                labels = UserRoleBodyLabels(
                    actions = labels.dashboard.roles.actions.getRoles(),
                    permission = labels.dashboard.roles.permissions,
                    userAssigned = labels.dashboard.roles.assigned
                )
            )
        ),
        add = addRole,
        manage = manageRoles,
        )
}