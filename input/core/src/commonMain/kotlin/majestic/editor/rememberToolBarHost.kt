package majestic.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberToolBarHost(vararg toolBars: ToolBar): ToolbarHost {
    return remember(toolBars) {   ToolbarHost(toolBars.toList()) }
}