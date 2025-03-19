package majestic.editor.body.chunksUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Paragraph
import majestic.editor.toolbar.EditorColors

@Composable
fun Chunks(
    controller: EditorBodyController,
    modifier: Modifier = Modifier,
    colors: EditorColors,
    actions: @Composable (chunk: Chunk) -> Unit,
    labels: Labels
) {
    val listState = rememberLazyListState()

    LaunchedEffect(controller.chunks.size) {
        if (controller.chunks.isNotEmpty()) {
            listState.scrollToItem(controller.chunks.size - 1)
        }
    }

    LazyColumn(
        modifier,
        state = listState
    ) {

        items(controller.chunks, key = { it.uid }) { chunk ->
            Column(
                modifier = Modifier.fillParentMaxWidth().wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top)
            ) {
                actions(chunk)
                when (chunk) {
                    is Heading -> HeadingChunk(
                        heading = chunk,
                        colors = colors,
                        labels = labels
                    )

                    is Paragraph -> ParagraphChunk(
                        paragraph = chunk,
                        colors = colors,
                        labels
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}