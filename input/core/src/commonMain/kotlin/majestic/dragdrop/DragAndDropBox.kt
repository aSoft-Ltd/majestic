package majestic.dragdrop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.NoRippleInteractionSource
import majestic.colors.ColorPair

class DragAndBorderColors(
    val hovered: Color,
    val pending: Color
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropBox(
    description: String,
    modifier: Modifier = Modifier,
    icon: Painter,
    colors: ColorPair = ColorPair(
        background = Color(0xFF1D2430),
        foreground = Color(0xFFFAFAFA),
    ),
    border: DragAndBorderColors = DragAndBorderColors(
        hovered = Color(0xFF007AFF),
        pending = Color(0xFF475467)
    ),
    onClick: () -> Unit = {},
    onDrop: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    if (isHovered) 2.dp else 1.dp,
                    if (isHovered) border.hovered else border.pending,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(colors.background)
                .hoverable(interactionSource = interactionSource)
                .clickable(
                    interactionSource = NoRippleInteractionSource,
                    indication = null,
                    enabled = true,
                    onClick = onClick
                )
                .dragAndDropTarget(
                    shouldStartDragAndDrop = { true },
                    target = remember {
                        object : DragAndDropTarget {
                            override fun onDrop(event: DragAndDropEvent): Boolean {
                                onDrop()
                                return true
                            }
                        }
                    },
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(300.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(colors.foreground.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(colors.foreground.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = icon,
                            tint = colors.foreground.copy(alpha = if (isHovered) 0.8f else 0.5f),
                            contentDescription = "Upload Icon"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = colors.foreground.copy(alpha = if (isHovered) 0.8f else 0.5f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
