package majestic.shared.tools.breadcrumbs

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import majestic.tooling.onClick

class Crumb(val label: String, val url: String)

@Composable
fun Breadcrumb(
    color: Color,
    items: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier
) = Row(modifier = modifier) {
    for ((index, item) in items.withIndex()) {
        item()
        if (index < items.lastIndex) {
            Text(
                text = " / ",
                fontSize = 14.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (index == items.lastIndex - 1) color else color.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun Breadcrumb(
    color: Color,
    items: List<Crumb>,
    onClick: (Crumb) -> Unit,
    modifier: Modifier = Modifier
) = Breadcrumb(
    modifier = modifier,
    color = color,
    items = items.mapIndexed { index, item ->
        {
            Text(
                text = item.label,
                fontSize = 14.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (index == items.lastIndex) color else color.copy(alpha = 0.5f),
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick { onClick(item) },
            )
        }
    }
)
