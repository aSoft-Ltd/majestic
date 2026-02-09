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
import composex.screen.orientation.ScreenOrientation
import majestic.LazyTable
import majestic.ThemeColor
import majestic.editor.toolbar.underline
import majestic.icons.Res
import majestic.icons.allDrawableResources
import majestic.icons.ic_calendar
import majestic.icons.ic_clock_01
import majestic.icons.ic_key
import majestic.icons.ic_laptop_phone
import majestic.icons.ic_user_multiple
import majestic.icons.tz_flag
import majestic.shared.menu.MenuOption
import majestic.shared.users.UsersLabels
import majestic.shared.users.label.table.StatusLabels
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
import majestic.users.dashboard.tools.portraitHeader.PortraitHeader
import majestic.users.dashboard.tools.rememberActiveView
import majestic.users.table.ListItem
import majestic.users.table.ListLabels
import majestic.users.table.tools.data.avatars
import majestic.users.table.tools.data.getOptions
import majestic.users.table.tools.data.permissions
import majestic.users.table.tools.data.roles
import majestic.shared.users.tools.HeaderIcons
import majestic.shared.users.tools.UsersData
import majestic.users.tools.data.separator
import nation.Country
import symphony.columnsOf
import symphony.linearPaginatorOf
import symphony.tableOf

internal fun UserRole.stats(labels: UserRoleBodyLabels) = listOf(
    Stat(
        icon = Res.drawable.ic_key,
        description = "Key Icon",
        title = labels.permission,
        value = permissions
    ),
    Stat(
        icon = Res.drawable.ic_user_multiple,
        description = "Users Icon",
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
    modifier: Modifier = Modifier,
    manageUsers: () -> Unit,
    manageRoles: () -> Unit,
    addUser: () -> Unit,
    addRole: () -> Unit,
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

    PortraitHeader(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                props.tabs.background.copy(.03f)
            )
            .height(65.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        colors = props.tabs,
        add = {
            when (it) {
                Users -> addUser()
                Roles -> addRole()
            }
        },
        manage = {
            when (it) {
                Users -> manageUsers()
                Roles -> manageRoles()
            }
        },
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
                menuAction = {
                    MenuOption(
                        orientation = orientation,
                        actions = getOptions(labels.table),
                        colors = props.table.body.colors.row.menuOption
                    ) { action -> }
                },
                labels = ListLabels(
                    role = props.table.body.labels.columns.roles,
                    permission = props.table.body.labels.columns.permission,
                    status = StatusLabels(
                        invited = props.table.body.labels.status.invited,
                        active = props.table.body.labels.status.active,
                        declined = props.table.body.labels.status.declined,
                        revoked = props.table.body.labels.status.revoked
                    )
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
                                    permission = labels.dashboard.roles.permissions,
                                    userAssigned = labels.dashboard.roles.assigned
                                )
                            )
                        )
                    ),
                    orientation = orientation,
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