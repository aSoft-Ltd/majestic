package majestic.shared.users.tools.colors.profile.contacts

import majestic.ThemeColor
import majestic.shared.profiles.contacts.phone.PhoneVerificationFormColors
import majestic.shared.tools.colors.toSubmitColors

internal fun ThemeColor.toPhoneVerificationFormColors() = PhoneVerificationFormColors(
    otp = toOtpInputColors(),
    submit = toSubmitColors(),
    sentCode = surface.contra.color,
    enterCode = surface.contra.color,
    number = surface.contra.color,
    sendCode = surface.contra.color,
    changeContact = surface.contra.color
)