package majestic.editor.body.chunksUI.tools.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import majestic.NoRippleInteractionSource
import majestic.editor.tools.EditorColors

@Composable
internal fun ListAction(
    modifier: Modifier,
    colors: EditorColors,
    onClick: () -> Unit,
    resource: ImageVector,
    hovered: Boolean,
    interactionSource: MutableInteractionSource
) = Box(
    modifier = modifier
        .hoverable(interactionSource = interactionSource)
        .clickable(
            interactionSource = NoRippleInteractionSource,
            indication = null,
            onClick = onClick
        ),
    contentAlignment = Alignment.Center,
) {
    Icon(
        modifier = Modifier
            .fillMaxSize(.8f)
            .padding(8.dp),
        imageVector = resource,
        contentDescription = null,
        tint = if (hovered) colors.text.active else colors.text.inActive
    )
}