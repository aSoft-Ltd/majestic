package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.runtime.Composable
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.tools.colors.toBackgroundsColor
import majestic.shared.tools.colors.toFloatingButtonColors

@Composable
fun ThemeColor.toContactColors(orientation: ScreenOrientation) = ContactsColors(
    floatingButton = toFloatingButtonColors(),
    email = toEmailColors(orientation = orientation),
    phone = toPhoneColors(orientation = orientation),

    item = toContactItemBackground(),
    backgrounds = toBackgroundsColor(),
    theme = this
)