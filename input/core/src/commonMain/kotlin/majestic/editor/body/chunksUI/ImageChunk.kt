package majestic.editor.body.chunksUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import kiota.Cancelled
import kiota.Denied
import kiota.Failure
import kiota.File
import kiota.FileManager
import kiota.MB
import kiota.file.mime.Image
import kiota.toImageBitmap
import kotlinx.coroutines.launch
import majestic.colors.ColorPair
import majestic.dragdrop.DragAndBorderColors
import majestic.dragdrop.DragAndDropBox
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.tools.EditorColors

private suspend fun FileManager.getBitmapPainter(file: File) = BitmapPainter(toImageBitmap(file))

@Composable
internal fun ImageChunk(
    resource: Painter,
    labels: Labels,
    colors: EditorColors,
    files: FileManager,
    previewOverlay: @Composable BoxScope.() -> Unit,
    permissionRequest: @Composable ((Boolean) -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    var painter by remember { mutableStateOf<BitmapPainter?>(null) }
    var showPermissionPrompt by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = if (painter == null) colors.background else Color.Transparent, shape = RoundedCornerShape(20.dp))
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        if (painter == null) {
            DragAndDropBox(
                modifier = Modifier.fillMaxSize(),
                icon = resource,
                description = labels.instructions,
                onClick = {
                    scope.launch {
                        when (val res = files.picker(listOf(Image.PNG,Image.JPG, Image.JPEG), limit = 10.MB).open()) {
                            is Cancelled -> {}
                            is Denied -> showPermissionPrompt = true
                            is Failure -> {}
                            is File -> painter = files.getBitmapPainter(res)
                        }
                    }
                },
                onDrop = {},
                colors = ColorPair(
                    background = colors.background,
                    foreground = colors.foreground
                ),
                border = DragAndBorderColors(
                    hovered = colors.text.active,
                    pending = colors.text.inActive
                )
            )
        } else {
            Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                painter?.let {
                    Image(
                        painter = it,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .hoverable(interactionSource = interactionSource)
                            .clickable {
                                scope.launch {
                                    when (val res = files.picker(listOf(Image.PNG,Image.JPG, Image.JPEG), limit = 10.MB).open()) {
                                        is Cancelled -> {}
                                        is Denied -> showPermissionPrompt = true
                                        is Failure -> {}
                                        is File -> painter = files.getBitmapPainter(res)
                                    }
                                }
                            }
                    )
                    if (isHovered) {
                        previewOverlay()
                    }
                }
            }
        }
    }

    if (showPermissionPrompt) {
        permissionRequest { shouldRequest ->
            
        }
    }
}