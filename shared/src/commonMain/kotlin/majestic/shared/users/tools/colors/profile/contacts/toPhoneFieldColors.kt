package majestic.shared.users.tools.colors.profile.contacts

import majestic.PhoneFieldColors
import majestic.PhoneFieldMicroColors
import majestic.ThemeColor

fun ThemeColor.toPhoneFieldColors() = PhoneFieldColors(
    focused = PhoneFieldMicroColors(
        text = surface.contra.color,
        border = dominant.actual.color,
        placeholder = surface.contra.color.copy(alpha = 0.4f)
    ),
    blurred = PhoneFieldMicroColors(
        text = surface.contra.color.copy(alpha = 0.8f),
        border = dominant.actual.color.copy(alpha = 0.5f),
        placeholder = surface.contra.color.copy(alpha = 0.4f)
    ),
)