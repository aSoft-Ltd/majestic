package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.ThemeColor
import majestic.buttons.ButtonColors
import majestic.shared.profiles.contacts.phone.PhoneFormColors
import majestic.shared.tools.colors.toPopMainColors

fun ThemeColor.toPhoneFormColors() = PhoneFormColors(
    phoneField = toPhoneFieldColors(),
    info = Color(0xFFE5A134),
    label = surface.contra.color.copy(alpha = 0.7f),
    leadingIcon = ColorPair(
        background = toPopMainColors().background,
        foreground = toPopMainColors().foreground
    ),
    search = ColorPair(
        background = surface.actual.color,
        foreground = toPopMainColors().foreground
    ),
    codePreview = surface.contra.color,
    button = ColorPair(
        foreground = toPopMainColors().foreground,
        background = toPopMainColors().background
    ),
    action = ButtonColors(
        contentColor = surface.actual.color,
        containerColor = surface.contra.color
    )
)