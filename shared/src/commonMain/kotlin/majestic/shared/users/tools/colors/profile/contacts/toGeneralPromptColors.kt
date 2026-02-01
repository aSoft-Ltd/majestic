package majestic.shared.users.tools.colors.profile.contacts

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import majestic.ThemeColor
import majestic.shared.profiles.contacts.tools.dialogs.GeneralPromptColors
import majestic.shared.tools.colors.toCancelColors
import majestic.shared.tools.colors.toSubmitColors

fun ThemeColor.toGeneralPromptColors(delete: Boolean = false) = GeneralPromptColors(
    title = surface.contra.color,
    description = surface.contra.color,
    warning = if (delete) Color.Red.copy(.7f).compositeOver(surface.contra.color) else Color(
        0xFFE5A134
    ),
    contact = surface.contra.color,
    buttonBorder = surface.contra.color,
    cancel = toCancelColors(),
    submit = toSubmitColors()
)