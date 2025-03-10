package majestic.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberToolBar(
    name: String,
    content: @Composable () -> Unit
): ToolBar {
    return remember(name, content) {
        ToolBar(name, content)
    }
}