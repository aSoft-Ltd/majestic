package majestic.shared.users.dashboard

import androidx.compose.ui.graphics.Color
import majestic.buttons.ButtonColors

data class HeaderColors(
    val background: Color,
    val separator: Color? = null,
    val manage: ButtonColors,
    val add: Color,
    val tint: Color,
    val title: Color
)