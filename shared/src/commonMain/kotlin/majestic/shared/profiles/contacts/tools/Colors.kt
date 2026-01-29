package majestic.shared.profiles.contacts.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.ThemeColor
import majestic.shared.profiles.contacts.email.EmailColors
import majestic.shared.profiles.contacts.phone.Backgrounds
import majestic.shared.profiles.contacts.phone.PhoneColors

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
    val theme: ThemeColor,
    val floatingButton: ColorPair,
    val backgrounds: Backgrounds,
    val email: EmailColors,
    val phone: PhoneColors
)
