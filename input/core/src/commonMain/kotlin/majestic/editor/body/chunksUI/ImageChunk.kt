package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair
import majestic.dragdrop.DragAndBorderColors
import majestic.dragdrop.DragAndDropBox
import majestic.editor.body.chunks.Image
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ImageChunk(
    resource: Painter,
    labels: Labels,
    colors: EditorColors,
    image: Image,
    onImagePick: (Image) -> Unit,
    imagePreview: @Composable (Image) -> Unit,
) = Column(
    modifier = Modifier
        .wrapContentSize()
        .background(color = Color.Transparent, shape = RoundedCornerShape(20.dp))
) {
    if (image.painter == null) {
        DragAndDropBox(
            modifier = Modifier.fillMaxSize(),
            icon = resource,
            description = labels.instructions,
            onClick = { onImagePick(image) },
            onDrop = { onImagePick(image) },
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
    if (image != null) {
        imagePreview(image)
    }
}