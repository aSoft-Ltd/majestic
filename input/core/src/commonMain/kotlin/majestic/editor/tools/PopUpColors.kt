package majestic.editor.tools

import androidx.compose.ui.graphics.Color




data class PopUpColors(
    val background: StateColors = StateColors(
        focused = Color(0xFF1E1E1E),
        unfocused = Color(0xFF2D2D2D)
    ),
    val field: FieldColors = FieldColors(
        text = StateColors(Color.Black, Color.Gray),
        border = StateColors(Color.Black, Color.Gray)
    )
)