package majestic.editor.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier,
    titleBar: @Composable () -> Unit,
    coverPhoto: @Composable () -> Unit,
    toolBarHost: @Composable () -> Unit,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
) {
    titleBar()
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        coverPhoto()
        toolBarHost()
    }
}