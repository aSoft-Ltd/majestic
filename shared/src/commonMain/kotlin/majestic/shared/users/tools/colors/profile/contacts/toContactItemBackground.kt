package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import majestic.ThemeColor
import majestic.shared.profiles.contacts.tools.ContactBackgroundColors
import majestic.shared.profiles.contacts.tools.ContactsItemBackground
import majestic.shared.profiles.contacts.tools.ProfileBackgroundColors
import majestic.shared.tools.colors.toBackground

fun ThemeColor.toContactItemBackground(): ContactsItemBackground = ContactsItemBackground(
    content = surface.contra.color,
    contact = ContactBackgroundColors(
        focused = toBackground
            .copy(alpha = .3f)
            .compositeOver(surface.contra.color.copy(.05f)),
        unfocused = Color.Transparent
    ),
    profile = ProfileBackgroundColors(
        focused = toBackground
            .copy(alpha = .3f)
            .compositeOver(surface.contra.color.copy(.05f)),
        unfocused = toBackground.copy(alpha = .5f)
    )
)