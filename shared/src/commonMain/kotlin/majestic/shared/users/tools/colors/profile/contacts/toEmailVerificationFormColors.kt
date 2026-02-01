package majestic.shared.users.tools.colors.profile.contacts

import majestic.ThemeColor
import majestic.shared.profiles.contacts.email.EmailVerificationFormColors
import majestic.shared.tools.colors.toSubmitColors

fun ThemeColor.toEmailVerificationFormColors() = EmailVerificationFormColors(
    buttonColors = toSubmitColors(),
    otp = toOtpInputColors(),
    description = surface.contra.color.copy(.5f),
    email = surface.contra.color,
    enterCode = surface.contra.color
)