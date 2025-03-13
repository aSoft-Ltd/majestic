package majestic.editor.insert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberInsert(
    name: String,
    content: @Composable () -> Unit
): Insert {
    return remember(name, content) {
        Insert(name, content)
    }
}