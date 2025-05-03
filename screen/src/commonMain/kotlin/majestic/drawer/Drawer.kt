package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class Drawer internal constructor(
    internal val span: DrawerSpan,
    internal val position: DrawerPosition,
    internal val display: DrawerDisplay,
    internal val background: Color,
    internal val content: @Composable BoxScope.(MultiDrawerController) -> Unit
)