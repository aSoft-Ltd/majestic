package majestic.editor.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.body.chunksUI.DropDownColors

class EditorColors(
    val text: ToolButtonColor = ToolButtonColor(Color.White, Color.Gray),
    val underline: ToolButtonColor = ToolButtonColor(Color(0xFF0061FF), Color.Gray),
    val background: Color = Color.Transparent,
    val foreground: Color = Color.White,
    val brush: Color = Color.White,
    val dropDown: DropDownColors = DropDownColors()
)

fun Modifier.underline(
    color: Color = Color.Black,
    width: Dp = 2.dp
) = drawBehind {
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = width.toPx(),
    )
}

@Composable
fun ToolBarHost(
    modifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight(),
    controller: ToolBarHostController,
    colors: EditorColors = EditorColors(),
    style: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500)
    )
) {

    val selected by controller.selected
    val activeColor = colors.underline.active
    val inActiveColor = colors.underline.inActive

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.Start),
            verticalAlignment = Alignment.Top
        ) {
            controller.tabs.forEach { toolBar ->
                val active = selected == toolBar

                val interactionSource = remember { MutableInteractionSource() }
                val hovered by interactionSource.collectIsHoveredAsState()

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable(onClick = { controller.select(toolBar) })
                        .hoverable(interactionSource = interactionSource)
                        .underline(
                            color = when {
                                active -> activeColor
                                hovered -> inActiveColor.copy(alpha = 178F)
                                else -> Color.Transparent
                            },
                            width = 2.dp
                        )
                        .padding(bottom = 8.dp),
                    text = toolBar.name,
                    style = style.copy(
                        color = when {
                            active -> colors.text.active
                            else -> colors.text.inActive
                        }
                    )
                )
            }
        }
        selected.content()
    }
}