package majestic.users.table.tools.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.tools.dialogs.Flex

@Composable
internal fun ActionBar(
    orientation: ScreenOrientation,
    navigator: Navigator,
    theme: ThemeColor,
    modifier: Modifier = Modifier,
    content: (@Composable RowScope.(ScreenOrientation, ThemeColor) -> Unit)? = null
) = Flex(
    modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp, horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    alignment = Alignment.CenterVertically,
    orientation = orientation
) {
    Breadcrumbs(
        url = navigator.current().path,
        theme = theme,
        onClick = { navigator.navigate(it) }
    )
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        if (content != null) content(orientation, theme)
    }
}