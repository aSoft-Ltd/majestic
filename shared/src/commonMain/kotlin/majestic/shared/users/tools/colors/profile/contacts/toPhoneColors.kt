package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.runtime.Composable
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.profiles.contacts.phone.PhoneColors
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogContentColors
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogsColors
import majestic.shared.tools.colors.rememberUserDialogColors
import majestic.shared.tools.colors.toBackgroundsColor
import majestic.shared.tools.colors.toToolTipColorPair
import majestic.shared.tools.colors.toUserMenuOptionColors

@Composable
fun ThemeColor.toPhoneColors(orientation: ScreenOrientation) = PhoneColors(
    menuOption = toUserMenuOptionColors(orientation = orientation),
    tint = surface.contra.color.copy(0.5f),
    title = surface.contra.color,
    separator = surface.contra.color.copy(0.5f),
    primary = surface.contra.color.copy(0.5f),
    tooltip = toToolTipColorPair(),
    dialog = toPhoneDialogColors(orientation),
    primaryBackground = surface.contra.color.copy(0.2f),
)

@Composable
fun ThemeColor.toPhoneDialogColors(orientation: ScreenOrientation): PhoneDialogsColors =
    PhoneDialogsColors(
        backgrounds = toBackgroundsColor(),
        dialog = rememberUserDialogColors(orientation = orientation),
        bar = toBarColors(),
        content = PhoneDialogContentColors(
            form = toPhoneFormColors(),
            verification = toPhoneVerificationFormColors(),
            prompts = toPromptColors()
        )
    )