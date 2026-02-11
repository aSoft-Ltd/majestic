package majestic.shared.attachment.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick

internal fun Modifier.iconButton(
    background: Color,
    onClick: () -> Unit = {}
) = size(35.dp)
    .clip(CircleShape)
    .background(background)
    .pointerHoverIcon(PointerIcon.Hand)
    .onClick(onClick)
    .padding(8.dp)
