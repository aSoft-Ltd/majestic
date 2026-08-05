package majestic.shared.appbar.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick

@Composable
fun MacOsActions(
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {},
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Box(
        modifier = Modifier.size(15.dp)
            .clip(CircleShape)
            .background(Color(0xFFE93F30))
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onClose)
    )

    Box(
        modifier = Modifier.size(15.dp)
            .clip(CircleShape)
            .background(Color(0xFFF2AD21))
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onMinimize)
    )

    Box(
        modifier = Modifier.size(15.dp)
            .clip(CircleShape)
            .background(Color(0xFFF47AC3A))
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onMaximize)
    )
}
