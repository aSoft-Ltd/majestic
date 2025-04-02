package majestic.editor.body.chunksUI.tools

import androidx.compose.ui.graphics.Color

data class StateColors(
    val focused: Color,
    val unfocused: Color
)

data class FieldColors(
    val text: StateColors,
    val border: StateColors
)

data class DropDownColors(
    val background: Color = Color.White,
    val field: FieldColors = FieldColors(
        text = StateColors(Color.Black, Color.Gray),
        border = StateColors(Color.Black, Color.Gray)
    )
)