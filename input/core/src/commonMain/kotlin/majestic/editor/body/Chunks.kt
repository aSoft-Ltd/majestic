package majestic.editor.body

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.BorderlessInput
import majestic.editor.ToolBarTabColors
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Paragraph

@Composable
fun Chunks(
    chunks: SnapshotStateList<Chunk>,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier){
        val m = Modifier.background(Color.Gray.copy(alpha = 0.9f)).border(width = 2.dp, color = Color.Red)
        items(chunks) { chunk ->
            when (chunk) {
                is Heading -> HeadingChunk(chunk, m.height(100.dp))
                is Paragraph -> ParagraphChunk(chunk, m.height(350.dp))
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun HeadingChunk(heading: Heading, modifier: Modifier) {
    var text by remember { mutableStateOf(heading.text) }
    BorderlessInput(
        title = text,
        onChange = {
            text = it
            heading.text = it
        },
        colors = ToolBarTabColors(),
        modifier = modifier,
        style = TextStyle(
            fontSize = when (heading.level) {
                1 -> 32.sp
                2 -> 20.sp
                3 -> 16.sp
                else -> 14.sp
            }
        )
    )
}

@Composable
private fun ParagraphChunk(paragraph: Paragraph, modifier: Modifier) {
    var text by remember { mutableStateOf(paragraph.text) }
    BorderlessInput(
        title = text,
        onChange = {
            text = it
            paragraph.text = it
        },
        colors = ToolBarTabColors(),
        modifier = modifier
    )
}