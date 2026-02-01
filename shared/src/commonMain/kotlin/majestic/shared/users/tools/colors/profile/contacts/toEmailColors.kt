package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.profiles.contacts.email.EmailColors
import majestic.shared.profiles.contacts.email.EmailFormColors
import majestic.shared.profiles.contacts.email.PromptColors
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogContentColors
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogsColors
import majestic.shared.profiles.contacts.phone.Backgrounds
import majestic.shared.profiles.contacts.tools.dialogs.BarColors
import majestic.shared.tools.colors.*

@Composable
fun ThemeColor.toEmailColors(orientation: ScreenOrientation) = EmailColors(
    menuOption = toUserMenuOptionColors(orientation = orientation),
    tint = surface.contra.color.copy(0.5f),
    title = surface.contra.color,
    separator = surface.contra.color.copy(0.5f),
    primary = surface.contra.color.copy(0.5f),
    primaryBackground = surface.contra.color.copy(0.2f),
    dialog = toEmailDialogColors(orientation)
)

@Composable
fun ThemeColor.toEmailDialogColors(orientation: ScreenOrientation): EmailDialogsColors =
    EmailDialogsColors(
        backgrounds = Backgrounds(
            portrait = surface.actual.color,
            landscape = toBackground
        ),
        dialog = rememberUserDialogColors(orientation = orientation),
        bar = toBarColors(),
        content = EmailDialogContentColors(
            form = toEmailFormColors(),
            verification = toEmailVerificationFormColors(),
            prompts = toPromptColors()
        )
    )

fun ThemeColor.toPromptColors(): PromptColors = PromptColors(
    warning = toGeneralPromptColors(),
    delete = toGeneralPromptColors(delete = true)
)

fun ThemeColor.toEmailFormColors(): EmailFormColors = EmailFormColors(
    info = Color(0xFFE5A134),
    label = surface.contra.color.copy(alpha = 0.7f),
    field = toTextFieldColors(),
    button = toSubmitColors()
)

fun ThemeColor.toBarColors() = BarColors(
    title = surface.contra.color,
    tint = surface.contra.color,
    iconBackground = surface.contra.color.copy(0.2f)
)