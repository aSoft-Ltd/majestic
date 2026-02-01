package majestic.shared.users.tools.colors.profile.contacts

import majestic.ThemeColor
import majestic.shared.profiles.contacts.tools.dialogs.BorderColors
import majestic.shared.profiles.contacts.tools.dialogs.OtpInputColors
import majestic.shared.tools.colors.focusedBorder
import majestic.shared.tools.colors.unFocusedBorder

fun ThemeColor.toOtpInputColors() = OtpInputColors(
    dominantActual = dominant.actual.color,
    dominantContra = dominant.contra.color,
    border = BorderColors(
        focused = focusedBorder(),
        unFocused = unFocusedBorder()
    )
)
