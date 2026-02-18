package majestic.shared.users.profile

import majestic.icons.*
import majestic.shared.profiles.roles.data.Role.Companion.roles
import majestic.shared.users.data.avatars
import majestic.shared.users.data.permissions
import majestic.shared.users.tools.HeaderIcons
import majestic.shared.users.tools.UsersData
import org.jetbrains.compose.resources.DrawableResource

fun toRandomUser(resource: DrawableResource?) = UsersData.getUserData(
    avatars = avatars(),
    permissionList = permissions(),
    roles = roles(),
    headerIcons = HeaderIcons(
        calendar = Res.drawable.ic_calendar_plain,
        phone = Res.drawable.ic_phone,
        time = Res.drawable.ic_timer,
        flag = resource ?: Res.drawable.tz_flag
    )
)