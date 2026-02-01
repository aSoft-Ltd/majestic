package majestic.shared.users.tools.colors.profile.permissions

import majestic.ThemeColor
import majestic.shared.profiles.permissions.GeneralPermissionColors
import majestic.shared.tools.colors.toBackground
import majestic.shared.users.tools.colors.profile.contacts.toDetailColors


fun ThemeColor.toGeneralPermissionColors() = GeneralPermissionColors(
    whiteBackground = surface.contra.color,
    clientBackground = toBackground,
    permission = toPermissionColors(),
    detail = toDetailColors()
)