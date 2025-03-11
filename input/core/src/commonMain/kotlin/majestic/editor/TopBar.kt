package majestic.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier,
    title: String = "Untitled",
    onChange: (String) -> Unit,
    onGoBack: () -> Unit = {},
    backButtonResource: Painter,
    colors: ToolBarTabColors,
    coverPhoto: @Composable () -> Unit,
    toolBarHost: @Composable () -> Unit,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Icon(
            painter = backButtonResource,
            tint = colors.foreground,
            contentDescription = "",
            modifier = Modifier.size(30.dp).clickable(onClick = onGoBack)
        )

        BorderlessInput(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .padding(4.dp),
            title = title,
            onChange = onChange,
            colors = colors,
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        coverPhoto()
        toolBarHost()
    }
}