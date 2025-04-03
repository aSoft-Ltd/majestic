package majestic.editor.tools

import androidx.compose.ui.graphics.Color
import majestic.editor.toolbar.ToolButtonColor

class EditorColors(
    val text: ToolButtonColor = ToolButtonColor(Color.White, Color.Gray),
    val underline: ToolButtonColor = ToolButtonColor(Color(0xFF0061FF), Color.Gray),
    val background: Color = Color.Transparent,
    val foreground: Color = Color.White,
    val brush: Color = Color.White,
    val popUp: PopUpColors = PopUpColors()
)