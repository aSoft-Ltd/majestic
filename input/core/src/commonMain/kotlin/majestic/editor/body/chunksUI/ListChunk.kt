package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.editor.BorderlessInput
import majestic.editor.body.chunks.List
import majestic.editor.body.chunks.lists.Type
import majestic.editor.body.chunksUI.tools.EditorBodyController
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.body.chunksUI.tools.lists.ListAction
import majestic.editor.body.chunksUI.tools.lists.ListUtilities
import majestic.editor.body.chunksUI.tools.lists.actionButton
import majestic.editor.tools.EditorColors

data class ListChunkResources(
    val remove: ImageVector,
    val add: ImageVector
)

@Composable
internal fun ListChunk(
    chunk: List,
    colors: EditorColors,
    labels: Labels,
    orientation: ScreenOrientation,
    resource: ListChunkResources,
    controller: EditorBodyController,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        chunk.list.forEachIndexed { index, item ->
            val marker = when (val type = chunk.type) {
                is Type.Ordered -> ListUtilities.getMarker(type, index)
                is Type.UnOrdered -> ListUtilities.getMarker(type)
            }
            val itemInteractionSource = remember { MutableInteractionSource() }
            val isHovered by itemInteractionSource.collectIsHoveredAsState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 16.dp else 8.dp, alignment = Alignment.Start),
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = marker,
                        color = colors.text.active,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(end = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = colors.background,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize()
                    ) {
                        var textState by remember { mutableStateOf(item) }
                        BorderlessInput(
                            value = textState,
                            hint = labels.list,
                            onChange = {
                                textState = it
                                chunk.list[index] = it
                            },
                            colors = colors,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            singleLine = false,
                            minLines = 4,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                            )
                        )
                    }
                }
                ListAction(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .actionButton(colors),
                    onClick = {
                        if (chunk.list.size == 1) controller.remove(chunk) else controller.removeListItem(chunk, index)
                    },
                    colors = colors,
                    resource = resource.remove,
                    hovered = isHovered,
                    interactionSource = itemInteractionSource,
                )
            }
        }

        val addInteractionSource = remember { MutableInteractionSource() }
        val hovered by addInteractionSource.collectIsHoveredAsState()

        ListAction(
            modifier = Modifier.actionButton(colors, hovered = hovered),
            colors = colors,
            onClick = {
                controller.addListItem(chunk)
            },
            resource = resource.add,
            hovered = hovered,
            interactionSource = addInteractionSource
        )
    }
}