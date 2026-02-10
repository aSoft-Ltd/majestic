package majestic.shared.attachment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentPreview(
    onDismiss: () -> Unit,
    colors: ColorPair,
    title: String,
    file: DrawableResource,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Top)
) {
    PreviewHeader(
        colors = colors,
        title = title,
        onDismiss = onDismiss,
        modifier = Modifier.fillMaxWidth()
    )
    Image(
        painter = painterResource(file),
        contentDescription = null,
        modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
    )
    PreviewFooter(
        colors = colors,
        modifier = Modifier.clip(CircleShape)
            .background(colors.background)
            .align(Alignment.CenterHorizontally)
    )
}
