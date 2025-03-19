package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.BorderlessInput
import majestic.editor.body.chunks.Paragraph
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ParagraphChunk(
    paragraph: Paragraph,
    colors: EditorColors,
    labels: Labels
) = Box(
    modifier = Modifier
        .wrapContentSize()
        .background(color = colors.background, shape = RoundedCornerShape(12.dp))
) {
    var text by remember { mutableStateOf(paragraph.text) }

    BorderlessInput(
        value = text,
        hint = labels.paragraph,
        onChange = {
            text = it
            paragraph.text = it
        },
        colors = colors,
        modifier = Modifier.padding(25.dp).fillMaxWidth(),
        singleLine = false ,
        minLines = 4,
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(500),
        )
    )
}