package majestic.shared.tools.breadcrumb.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation

data class ContainerPadding(
    val start: Dp = 5.dp,
    val end: Dp = 5.dp,
    val bottom: Dp = 5.dp,
    val top: Dp = 5.dp
)

fun Modifier.breadCrumbTab(
    container: Color,
    orientation: ScreenOrientation,
    paddings: ContainerPadding = ContainerPadding()
) = this
    .pointerHoverIcon(PointerIcon.Hand)
    .clip(RoundedCornerShape(8.dp))
    .wrapContentSize()
    .background(
        shape = RoundedCornerShape(8.dp),
        color = if (orientation is Landscape) container else Color.Transparent
    )
    .padding(
        top = if (orientation is Landscape) paddings.top else 0.dp,
        bottom = if (orientation is Landscape) paddings.bottom else 0.dp,
        start = if (orientation is Landscape) paddings.start else 0.dp,
        end = if (orientation is Landscape) paddings.end else 0.dp
    )