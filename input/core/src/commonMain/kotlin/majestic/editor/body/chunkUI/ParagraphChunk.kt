package majestic.editor.body.chunkUI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.editor.BorderlessInput
import majestic.editor.body.ChunkWrapper
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.EditorControl
import majestic.editor.body.chunks.Paragraph
import majestic.editor.insert.Insert
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ParagraphChunk(
    paragraph: Paragraph,
    modifier: Modifier,
    colors: EditorColors,
    control: EditorControl,
    actions: @Composable (Chunk) -> Unit,
    customItemContent: @Composable (Insert) -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit
) = ChunkWrapper(
    modifier = modifier,
    colors = colors,
    control = control,
    actions = { actions(paragraph) },
    customItemContent = customItemContent,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
) {
    var text by remember { mutableStateOf(paragraph.text) }
    BorderlessInput(
        value = text,
        onChange = {
            text = it
            paragraph.text = it
        },
        colors = colors,
        modifier = Modifier.padding(25.dp).fillMaxWidth().height(350.dp),
        singleLine = false
    )
}