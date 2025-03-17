package majestic.editor.body.chunkUI

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.editor.body.chunks.EditorControl
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Paragraph
import majestic.editor.insert.Insert
import majestic.editor.toolbar.EditorColors

@Composable
fun Chunks(
    controller: EditorBodyController,
    modifier: Modifier = Modifier,
    colors: EditorColors,
    actions: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    customItemContent: @Composable (Insert) -> Unit = {},
    trailingIcon: @Composable () -> Unit = {}
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

        items(controller.chunks) { chunk ->
            when (chunk) {
                is Heading -> HeadingChunk(
                    heading = chunk,
                    modifier = Modifier.wrapContentHeight(),
                    colors = colors,
                    control = controller.editorControl,
                    actions = actions,
                    customItemContent = customItemContent,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )

                is Paragraph -> ParagraphChunk(
                    paragraph = chunk,
                    modifier = Modifier.wrapContentHeight(),
                    colors = colors,
                    control = controller.editorControl,
                    actions = actions,
                    customItemContent = customItemContent,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}