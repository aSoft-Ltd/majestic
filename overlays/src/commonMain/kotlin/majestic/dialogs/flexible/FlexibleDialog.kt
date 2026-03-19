package majestic.dialogs.flexible

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import composex.screen.orientation.ScreenOrientation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlexibleDialog(
    bar: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) = BasicAlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            bar()
        }
        content()
    }
}