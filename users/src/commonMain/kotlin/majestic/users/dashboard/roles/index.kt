package majestic.users.dashboard.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.editor.toolbar.underline
import majestic.users.dashboard.roles.tools.RoleAction
import majestic.users.dashboard.roles.tools.UserRole
import majestic.users.dashboard.roles.tools.stats
import majestic.users.tools.menu.OptionMenu

data class UserBodyProps(
    val colors: RoleCardColors,
    val labels: UserRoleBodyLabels
)

data class UserRoleBodyLabels(
    val actions: List<OptionMenu<RoleAction>>,
    val permission: String,
    val userAssigned: String,
)

data class UsersRolesProps(
    val header: HeaderProps,
    val body: UserBodyProps
)

@Composable
fun UsersRoles(
    modifier: Modifier,
    orientation: ScreenOrientation,
    props: UsersRolesProps,
    add: () -> Unit,
    manage: () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Header(
        modifier = Modifier
            .background(props.header.colors.background)
            .fillMaxWidth()
            .wrapContentHeight()
            .underline(
                color = props.header.colors.separator?.copy(.03f) ?: props.header.colors.background
            )
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .height(80.dp),
        props = props.header,
        add = add,
        manage = manage
    )

    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
        UserRole.roles.forEachIndexed { index, role ->
            RoleCard(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
                props = RoleCardProps(
                    colors = props.body.colors,
                    data = RoleCardData(
                        actions = props.body.labels.actions,
                        title = role.name,
                        body = role.description,
                        stats = props.stats(role)
                    )
                ),
                orientation = orientation
            )

            if (index != UserRole.roles.lastIndex) Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(.05.dp)
                    .underline(color = props.header.colors.add.copy(.05f), width = .5.dp)
            )
        }
    }
}