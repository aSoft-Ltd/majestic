package majestic.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import majestic.tools.CloseButton
import majestic.tools.closeButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    colors: DialogColors,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) = BasicAlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
) {
    Box {
        content()
        CloseButton(
            modifier = Modifier.closeButton(onDismiss, colors),
            tint = colors.cancelContent,
        )
    }
}