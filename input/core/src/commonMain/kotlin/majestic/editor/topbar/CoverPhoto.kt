package majestic.editor.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CoverPhoto(
    modifier: Modifier,
    coverPhoto: Painter?,
    onChangePhoto: () -> Unit,
    overlay: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .hoverable(interactionSource = interactionSource)
            .clickable(onClick = onChangePhoto)
    ) {

        if (coverPhoto != null) {
            Image(
                painter = coverPhoto,
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        if (isHovered) {
            overlay()
        }
    }
}