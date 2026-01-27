package majestic.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import majestic.tooling.onClick
import majestic.tools.CloseButton
import majestic.tools.closeButton
import org.jetbrains.compose.resources.DrawableResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    colors: DialogColors,
    onDismiss: () -> Unit,
    closeIcon: DrawableResource? = null,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) = BasicAlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
) {
    Box {
        content()

        if (closeIcon != null) CloseButton(
            modifier = Modifier.closeButton(colors).onClick(onDismiss),
            tint = colors.cancelContent,
        )
    }
}