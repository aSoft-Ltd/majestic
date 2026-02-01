package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.ui.graphics.Color
import majestic.ThemeColor
import majestic.shared.profiles.contacts.tools.dialogs.GeneralPromptColors
import majestic.shared.tools.colors.toCancelColors
import majestic.shared.tools.colors.toSubmitColors

fun ThemeColor.toDeleteGeneralPromptColors() = GeneralPromptColors(
    title = surface.contra.color,
    description = surface.contra.color,
    contact = surface.contra.color,
    warning = Color.Red,
    buttonBorder = surface.contra.color,
    cancel = toCancelColors(),
    submit = toSubmitColors()
)