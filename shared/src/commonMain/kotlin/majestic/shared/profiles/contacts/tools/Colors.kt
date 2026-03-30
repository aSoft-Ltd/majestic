package majestic.shared.profiles.contacts.tools

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.shared.profiles.contacts.email.EmailColors
import majestic.shared.profiles.contacts.phone.PhoneColors

data class ContactsItemColors(
    val content: Color,
    val separator: Color,
    val background: Color,
)

data class HazeColors(
    val edges: Color,
    val middle: Color
)

data class ContactsColors(
    val item: ContactsItemColors,
    val button: ColorPair,
    val hazeButton: Color,
    val hazeColors: HazeColors,
    val background: Color,
    val email: EmailColors,
    val phone: PhoneColors
)