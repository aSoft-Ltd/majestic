package majestic.users.table.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor


@Composable
internal fun UserTableActionBar(
    modifier: Modifier,
    orientation: ScreenOrientation,
    navigator: Navigator,
    theme: ThemeColor,
    label: String,
    export: @Composable () -> Unit,
    autoHide: @Composable () -> Unit
) = when (orientation) {
    is Landscape -> ActionBar(
        modifier = modifier,
        orientation = orientation,
        navigator = navigator,
        theme = theme
    ) { _, _ ->
        export()
    }

    is Portrait -> PortraitActionBar(
        modifier = modifier,
        onBack = { navigator.go(-1) },
        label = label,
        theme = theme,
        autoHide = autoHide
    )
}