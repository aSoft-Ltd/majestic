package majestic.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberToolBarHost(vararg toolBars: ToolBar): ToolBarHostController {
    return remember(toolBars) {   ToolBarHostController(toolBars.toList()) }
}