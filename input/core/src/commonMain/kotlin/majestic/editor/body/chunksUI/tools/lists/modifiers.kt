package majestic.editor.body.chunksUI.tools.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.editor.tools.EditorColors

internal fun Modifier.actionButton(colors: EditorColors, hovered: Boolean) = wrapContentSize()
    .clip(shape = RoundedCornerShape(8.dp))
    .background(
        color = if (hovered) colors.popUp.background.focused
        else colors.popUp.background.unfocused,
        shape = RoundedCornerShape(8.dp)
    )
    .padding(8.dp)