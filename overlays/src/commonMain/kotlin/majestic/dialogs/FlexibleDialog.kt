package majestic.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import majestic.dialogs.flexibleDialog.DefaultBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlexibleDialog(
    colors: DialogColors,
    onDismiss: () -> Unit,
    title: String = "",
    modifier: Modifier,
    orientation: ScreenOrientation,
    bar: @Composable BoxScope.(
        dialog: DialogColors
    ) -> Unit = {
        DefaultBar(
            orientation,
            onDismiss,
            it,
            title
        )
    },
    content: @Composable (title: String) -> Unit
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
            bar(colors)
        }
        content(title)
    }
}