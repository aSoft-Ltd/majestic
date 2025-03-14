package majestic.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberToolBarHostController(vararg toolBars: ToolBar): ToolBarHostController {
    return remember(toolBars.joinToString { it.name }) { ToolBarHostController(toolBars.toList()) }
}