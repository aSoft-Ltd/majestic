package majestic.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch

class ToolBarTabColors(
    val text: ToolButtonColor = ToolButtonColor(Color.White, Color.Gray),
    val bottomLine: ToolButtonColor = ToolButtonColor(Color(0xFF0061FF), Color.Gray)
)

fun Modifier.borderBottom(
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
fun ToolBarTabs(
    modifier: Modifier,
    toolBarHost: ToolbarHost,
    colors: ToolBarTabColors = ToolBarTabColors(),
    textStyle: (active: Boolean) -> TextStyle = { active ->
        TextStyle(
            fontSize = 13.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight(500),
            color = if (active) colors.text.active else colors.text.inActive.copy(alpha = 178F)
        )
    }

) {
    var selectedTabIndex by remember { mutableStateOf(0) }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            toolBarHost.tabs.forEachIndexed { index, toolBar ->
                val active = selectedTabIndex == index
                val activeColor = colors.bottomLine.active
                val inActiveColor = colors.bottomLine.inActive

                val interactionSource = remember { MutableInteractionSource() }
                var hovered by remember { mutableStateOf(false) }

                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        when (it) {
                            is HoverInteraction.Enter -> hovered = true
                            is HoverInteraction.Exit -> hovered = false
                        }
                    }
                }


                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable(onClick = { selectedTabIndex = index })
                        .padding(top = 8.dp)
                        .hoverable(interactionSource = interactionSource)
                        .borderBottom(
                            color = if (active) activeColor else if (hovered) inActiveColor.copy(alpha = 178F) else Color.Transparent,
                            width = 2.dp
                        )
                        .padding(bottom = 8.dp),
                    text = toolBar.name,
                    style = textStyle(active)
                )
            }
        }
        toolBarHost.tabs[selectedTabIndex].content()
    }
}