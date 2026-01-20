//package majestic.users.table
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import captain.Navigator
//import cinematic.watchAsState
//import composex.screen.orientation.Landscape
//import composex.screen.orientation.ScreenOrientation
//import majestic.ThemeColor
//import majestic.editor.toolbar.underline
//import majestic.users.dashboard.tools.toColumnLabels
//import majestic.users.labels.settings.observeUsersLabels
//import majestic.users.labels.settings.rememberLanguage
//import majestic.users.table.body.UsersTableBody
//import majestic.users.table.body.UsersTableBodyProperties
//import majestic.users.table.header.SelectionBar
//import majestic.users.table.header.SelectionBarProperties
//import majestic.users.table.header.tools.HeaderProperties
//import majestic.users.table.header.tools.UsersTableHeader
//import majestic.users.table.header.tools.getHeaderLabels
//import majestic.users.table.tools.UserTableActionBar
//import majestic.users.table.tools.data.avatars
//import majestic.users.table.tools.data.getOptions
//import majestic.users.table.tools.data.permissions
//import majestic.users.table.tools.data.roles
//import majestic.users.table.tools.data.toActions
//import majestic.users.tools.data.HeaderIcons
//import majestic.users.tools.data.UsersData
//import majestic.users.tools.data.getWeights
//import majestic.users.tools.menu.MenuOption
//import majestic.users.tools.menu.MenuOptionColors
//import nation.Country
//import symphony.columnsOf
//import symphony.linearPaginatorOf
//import symphony.tableOf
//import tz.co.asoft.majestic_users.generated.resources.Res
//import tz.co.asoft.majestic_users.generated.resources.allDrawableResources
//import tz.co.asoft.majestic_users.generated.resources.ic_calendar
//import tz.co.asoft.majestic_users.generated.resources.ic_clock_01
//import tz.co.asoft.majestic_users.generated.resources.ic_laptop_phone
//import tz.co.asoft.majestic_users.generated.resources.tz_flag
//
//
//internal fun Modifier.selectionBar(
//    theme: ThemeColor
//) = this
//    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
//    .fillMaxWidth()
//    .background(
//        theme.surface.actual.color,
//        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
//    )
//    .background(
//        color = theme.dominant.actual.color.copy(alpha = 0.1f),
//        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
//    )
//    .padding(vertical = 10.dp, horizontal = 12.dp)
//
//data class MainUserTableProps(
//    val theme: ThemeColor,
//    val barBackground: Color,
//    val background: Color,
//    val menuOption: MenuOptionColors,
//    val selection: SelectionBarProperties,
//    val head: HeaderProperties,
//    val body: UsersTableBodyProperties
//)
//
//@Composable
//fun MainUsersTable(
//    modifier: Modifier,
//    props: MainUserTableProps,
//    navigator: Navigator,
//    orientation: ScreenOrientation,
//    onItemClick: () -> Unit,
//    labels:UsersTable
//    export: @Composable () -> Unit,
//    autoHide: @Composable () -> Unit
//) = Column(modifier = modifier) {
//    val language by observeUsersLabels(rememberLanguage())
//    val labels = language.table
//    val paginator = remember { linearPaginatorOf<UsersData>(20) }
//    var selectionActive by remember { mutableStateOf(false) }
//    val country = Country.entries.random()
//    val key = remember(country) { "flag_" + country.code.lowercase() }
//    val flag = remember(key) { Res.allDrawableResources[key] }
//
//    val table = remember {
//        tableOf(
//            paginator = paginator,
//            columns = columnsOf {
//                column("checkbox", key = "checkbox") { it.item.selected }
//                column(labels.head.name) { it.item.fullName }
//                column(labels.head.email) { it.item.email }
//                column(labels.head.id) { it.item.id }
//                column(labels.head.dateJoined) { it.item.dateJoined }
//                column(labels.head.lastActive) { it.item.lastActive }
//                column(labels.head.status) { it.item.status }
//                column("", "action") { }
//            }
//        )
//    }
//
//    var perPage by remember { mutableStateOf(20) }
//    LaunchedEffect(perPage) {
//        paginator.initialize {
//            buildList {
//                repeat(perPage) {
//                    add(
//                        UsersData.getUserData(
//                            avatars(), permissions(), roles(),
//                            headerIcons = HeaderIcons(
//                                Res.drawable.ic_calendar,
//                                Res.drawable.ic_laptop_phone,
//                                Res.drawable.ic_clock_01,
//                                flag = flag ?: Res.drawable.tz_flag
//                            )
//                        )
//                    )
//                }
//            }
//        }
//    }
//    val columns = table.columns.current.watchAsState()
//    val weight = getWeights(
//        columns = columns,
//        labels = getHeaderLabels(labels.toColumnLabels())
//    )
//    UserTableActionBar(
//        modifier = Modifier
//            .background(color = props.barBackground)
//            .underline(props.theme.surface.contra.color.copy(alpha = 0.05f))
//            .fillMaxWidth()
//            .then(if (orientation is Landscape) Modifier.wrapContentHeight() else Modifier.height(70.dp))
//            .padding(horizontal = 16.dp, vertical = 16.dp),
//        orientation = orientation,
//        theme = props.theme,
//        navigator = navigator,
//        label = labels.head.users,
//        export = export,
//        autoHide = autoHide
//    )
//    UsersList(
//        modifier = Modifier
//            .clip(
//                shape = when (orientation) {
//                    is Landscape -> RoundedCornerShape(
//                        topStart = 20.dp,
//                        topEnd = 20.dp
//                    )
//
//                    else -> RoundedCornerShape(0.dp)
//                }
//            )
//            .padding(
//                top = if (orientation is Landscape && !selectionActive) 10.dp else if (selectionActive) 1.dp else 0.dp,
//                start = if (orientation is Landscape) 10.dp else 0.dp,
//                end = if (orientation is Landscape) 10.dp else 0.dp,
//                bottom = if (orientation is Landscape) 10.dp else 0.dp,
//            )
//            .fillMaxSize()
//            .background(
//                color = props.background.copy(0.5f),
//                shape = when (orientation) {
//                    is Landscape -> RoundedCornerShape(
//                        topStart = 20.dp,
//                        topEnd = 20.dp
//                    )
//
//                    else -> RoundedCornerShape(0.dp)
//                }
//            ),
//        table = table,
//        selectionBar = {
//            if (it > 0 && orientation is Landscape) SelectionBar(
//                modifier = Modifier.selectionBar(props.theme),
//                props = props.selection,
//                onActionClick = table.toActions()
//            )
//        },
//        header = { column, selectCount ->
//            if (orientation is Landscape) UsersTableHeader(
//                column = column,
//                weight = weight,
//                count = selectCount,
//                props = props.head,
//                table = table,
//            )
//        },
//    ) { cell, selected, cellHeight, _ ->
//        UsersTableBody(
//            orientation = orientation,
//            cell = cell,
//            cellHeight = cellHeight,
//            selected = selected,
//            table = table,
//            onItemClick = onItemClick,
//            weight = weight,
//            props = props.body,
//        ) {
//            MenuOption(
//                orientation = orientation,
//                actions = getOptions(labels),
//                colors = props.menuOption,
//            ) { action -> }
//        }
//    }
//}