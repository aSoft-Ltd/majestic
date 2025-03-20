package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair
import majestic.dragdrop.DragAndBorderColors
import majestic.dragdrop.DragAndDropBox
import majestic.editor.toolbar.EditorColors


@Composable
internal fun ImageChunk(
    resource: Painter,
    labels: Labels,
    colors: EditorColors,
    onDrop: () -> Unit,
    onClick: () -> Unit
) = Box(modifier = Modifier.wrapContentSize().background(color = colors.background, shape = RoundedCornerShape(20.dp))) {
    DragAndDropBox(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        icon = resource,
        description = labels.instructions,
        onClick = onClick, onDrop = onDrop,
        colors = ColorPair(
            background = colors.background,
            foreground = colors.foreground
        ),
        border = DragAndBorderColors(
            hovered = colors.text.active,
            pending = colors.text.inActive
        )
    )
}