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
import kotlinx.coroutines.launch
import majestic.colors.ColorPair
import majestic.dragdrop.DragAndBorderColors
import majestic.dragdrop.DragAndDropBox
import majestic.editor.toolbar.EditorColors
import majestic.filepicker.FilePicker
import majestic.filepicker.FileType

@Composable
internal fun ImageChunk(
    resource: Painter,
    labels: Labels,
    colors: EditorColors,
    picker: FilePicker,
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
                        if (picker.hasPermission()) {
                            picker.pickFiles(
                                config = FilePicker.Config(count = 3, type = listOf(FileType.IMAGE))
                            ).firstOrNull()?.let { fileInfo ->
                                painter = picker.getBitMap(fileInfo.path)
                            }
                        } else if (!showPermissionPrompt) {
                            showPermissionPrompt = true
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
                                    picker.pickFiles(
                                        config = FilePicker.Config(count = 3, type = listOf(FileType.IMAGE))
                                    ).firstOrNull()?.let { fileInfo ->
                                        painter = picker.getBitMap(fileInfo.path)
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
            scope.launch {
                if (shouldRequest) {
                    showPermissionPrompt = false
                    picker.requestPermission()
                }
                showPermissionPrompt = false
            }
        }
    }
}