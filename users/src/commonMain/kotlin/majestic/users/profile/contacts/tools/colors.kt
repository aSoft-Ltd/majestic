package majestic.users.profile.contacts.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.users.profile.contacts.email.EmailColors
import majestic.users.profile.contacts.email.EmailFormColors
import majestic.users.profile.contacts.phone.PhoneColors
import majestic.users.profile.contacts.phone.PhoneFormColors
import majestic.users.tools.ProfilePortraitHeaderColors
import majestic.users.tools.buttons.ButtonAnimateColors
import majestic.users.tools.buttons.FlatButtonColors

internal fun ThemeColor.toListCardColors(background: Color) = when (this) {
    is Dark -> ColorPair(
        background = background.copy(.3f),
        foreground = Color.Transparent
    )

    is Light -> ColorPair(
        background = background.copy(.7f),
        foreground = Color.Transparent
    )
}

data class ContactsColors(
    val background: Color,
    val profileHeader: ProfilePortraitHeaderColors,
    val theme: ThemeColor,
    val phoneForm: PhoneFormColors,
    val buttonAnimate: ButtonAnimateColors,
    val flatButton: FlatButtonColors,
    val floatingButton: ColorPair,
    val email: EmailColors,
    val emailForm: EmailFormColors,
    val phone: PhoneColors
)