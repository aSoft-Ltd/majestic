package majestic.users.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import composex.screen.orientation.ScreenOrientation
import majestic.LazyTable
import majestic.editor.toolbar.underline
import majestic.tooling.onClick
import majestic.users.dashboard.roles.RoleCard
import majestic.users.dashboard.roles.RoleCardData
import majestic.users.dashboard.roles.RoleCardProps
import majestic.users.dashboard.roles.UserRoleBodyLabels
import majestic.users.dashboard.roles.tools.Stat
import majestic.users.dashboard.roles.tools.UserRole
import majestic.users.dashboard.roles.tools.getRoles
import majestic.users.dashboard.tools.Roles
import majestic.users.dashboard.tools.Users
import majestic.users.dashboard.tools.View
import majestic.users.dashboard.tools.portraitHeader.PortraitHeader
import majestic.users.dashboard.tools.rememberActiveView
import majestic.users.dashboard.tools.toColumnLabels
import majestic.users.labels.UsersLabels
import majestic.users.table.ListItem
import majestic.users.table.ListLabels
import majestic.users.table.header.tools.getHeaderLabels
import majestic.users.table.header.tools.getStatusLabels
import majestic.users.table.tools.data.avatars
import majestic.users.table.tools.data.permissions
import majestic.users.table.tools.data.roles
import majestic.users.tools.data.HeaderIcons
import majestic.users.tools.data.UsersData
import majestic.users.tools.data.getDashboardWeights
import majestic.users.tools.data.separator
import nation.Country
import symphony.columnsOf
import symphony.linearPaginatorOf
import symphony.tableOf
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.allDrawableResources
import tz.co.asoft.majestic_users.generated.resources.ic_calendar
import tz.co.asoft.majestic_users.generated.resources.ic_clock_01
import tz.co.asoft.majestic_users.generated.resources.ic_key
import tz.co.asoft.majestic_users.generated.resources.ic_laptop_phone
import tz.co.asoft.majestic_users.generated.resources.ic_user_multiple
import tz.co.asoft.majestic_users.generated.resources.tz_flag

internal fun UserRole.stats(labels: UserRoleBodyLabels) = listOf(
    Stat(
        icon = Res.drawable.ic_key,
        iconContentDescription = "Key Icon",
        title = labels.permission,
        value = permissions
    ),
    Stat(
        icon = Res.drawable.ic_user_multiple,
        iconContentDescription = "Users Icon",
        title = labels.userAssigned,
        value = usersAssigned
    )
)

@Composable
internal fun PortraitView(
    orientation: ScreenOrientation,
    props: TableViewProps,
    labels: UsersLabels,
    onItemClick: () -> Unit = {},
    add: (view: View) -> Unit = {},
    manage: (view: View) -> Unit = {},
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
) {
    val paginator = remember { linearPaginatorOf<UsersData>(20) }
    val country = Country.entries.random()
    val key = remember(country) { "flag_" + country.code.lowercase() }
    val flag = remember(key) { Res.allDrawableResources[key] }
    val activeView = rememberActiveView()
    LaunchedEffect(activeView.view) { println("current active Selection is ${activeView.view}") }
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
    PortraitHeader(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                props.tabs.background.copy(.03f)
            )
            .height(65.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        colors = props.tabs,
        add = add,
        manage = manage,
        labels = labels.dashboard,
        navigator = activeView
    )
    when (activeView.view) {
        Users -> LazyTable(
            modifier = Modifier.weight(1f),
            table = table,
            columns = { _ ->
            }
        ) { cell ->
            val selected = table.selector.isRowSelectedOnCurrentPage(cell.row.number)
            ListItem(
                user = cell.row.item,
                colors = props.table.body.colors.listItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .onClick(callback = onItemClick)
                    .separator(
                        isLast = cell.row.index == table.rows.lastIndex,
                        color = props.table.body.colors.listItem.surfaceContra.copy(0.05f)
                    )
                    .padding(10.dp),
                menuAction = { },
                labels = ListLabels(
                    role = props.table.body.labels.columns.roles,
                    permission = props.table.body.labels.columns.permission,
                    status = getStatusLabels(props.table.body.labels.status)
                )
            )
        }

        Roles -> Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            UserRole.roles.forEachIndexed { index, role ->
                RoleCard(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
                    props = RoleCardProps(
                        colors = props.roles.roleCard,
                        data = RoleCardData(
                            actions = labels.dashboard.roles.actions.getRoles(),
                            title = role.name,
                            body = role.description,
                            stats = role.stats(
                                labels = UserRoleBodyLabels(
                                    actions = labels.dashboard.roles.actions.getRoles(),
                                    permission = "",
                                    userAssigned = ""
                                )
                            )
                        )
                    ),
                    orientation = orientation
                )

                if (index != UserRole.roles.lastIndex) Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(.05.dp)
                        .underline(color = props.roles.roleCard.body.copy(.05f), width = .5.dp)
                )
            }
        }
    }
}