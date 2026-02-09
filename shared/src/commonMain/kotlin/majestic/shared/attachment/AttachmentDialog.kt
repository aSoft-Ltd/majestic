package majestic.shared.attachment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import majestic.ColorPair
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentDialog(
    onDismiss: () -> Unit,
    colors: ColorPair,
    title: String,
    file: DrawableResource,
    modifier: Modifier = Modifier
) = BasicAlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
) {
    Column(
        modifier = Modifier.wrapContentSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Top)
    ) {
        DialogHeader(
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
        DialogFooter(
            colors = colors,
            modifier = Modifier.clip(CircleShape)
                .background(colors.background)
                .align(Alignment.CenterHorizontally)
        )
    }
}
