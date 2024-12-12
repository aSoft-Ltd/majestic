package majestic.drawer

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

class Drawer internal constructor(
    internal val position: DrawerPosition,
    internal val span: DrawerSpan,
    internal val display: DrawerDisplay,
    internal val content: @Composable BoxScope.() -> Unit
)