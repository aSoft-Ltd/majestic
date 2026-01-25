package profiles.contacts.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.buttons.ButtonAnimateColors
import majestic.buttons.FlatButtonColors
import profiles.contacts.email.EmailColors
import profiles.contacts.phone.Backgrounds
import profiles.contacts.phone.PhoneColors

data class ContactBackgroundColors(
    val focused: Color,
    val unfocused: Color
)

data class ProfileBackgroundColors(
    val focused: Color,
    val unfocused: Color
)

data class ContactsItemBackground(
    val content: Color,
    val contact: ContactBackgroundColors,
    val profile: ProfileBackgroundColors
)

data class ContactsColors(
    val item: ContactsItemBackground,
    val buttonAnimate: ButtonAnimateColors,
    val flatButton: FlatButtonColors,
    val floatingButton: ColorPair,
    val backgrounds: Backgrounds,
    val email: EmailColors,
    val phone: PhoneColors
)
