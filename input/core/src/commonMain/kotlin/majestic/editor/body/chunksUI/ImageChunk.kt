package majestic.editor.body.chunksUI

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import majestic.dragdrop.DragAndDropBox


@Composable
internal fun ImageChunk(
    resource: Painter, labels: Labels
) = DragAndDropBox(
    modifier = Modifier
        .fillMaxWidth()
        .height(155.dp),
    icon = resource,
    description = labels.instructions,
    onClick = { }, onDrop = { }
)