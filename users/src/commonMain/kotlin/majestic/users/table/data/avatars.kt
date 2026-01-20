package majestic.users.table.data

import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.useravatar1
import tz.co.asoft.majestic_users.generated.resources.useravatar2
import tz.co.asoft.majestic_users.generated.resources.useravatar3
import tz.co.asoft.majestic_users.generated.resources.useravatar4
import tz.co.asoft.majestic_users.generated.resources.useravatar5
import tz.co.asoft.majestic_users.generated.resources.useravatar6

fun avatars() = listOf<DrawableResource?>(
    Res.drawable.useravatar1,
    Res.drawable.useravatar2,
    Res.drawable.useravatar3,
    Res.drawable.useravatar4,
    Res.drawable.useravatar5,
    Res.drawable.useravatar6
)