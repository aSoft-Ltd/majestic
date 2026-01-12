package majestic.users.tools.dialogs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.graphics.Color

data class DialogColors(
    val containerColor: Color,
    val contentColor: Color,
    val cancelContent: Color,
    val cancelBackground: Color,
    val interactionSource: MutableInteractionSource, // TODO: Remove this because hii ni class ya rangi
)
