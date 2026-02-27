package majestic.editor.body.chunksUI.tools.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.editor.tools.EditorColors
import majestic.tools.withOverlay

internal fun Modifier.actionButton(colors: EditorColors, hovered: Boolean = false) = width(50.dp)
    .height(50.dp)
    .clip(shape = RoundedCornerShape(8.dp))
    .background(
        color = if (hovered) colors.containerBg.withOverlay(Color.White, 0.1f)
        else colors.containerBg,
        shape = RoundedCornerShape(8.dp)
    )