package majestic.editor.insert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberInsertHostController(vararg inserts: Insert): InsertHostController {
    return remember(inserts) { InsertHostController(inserts.toMutableList()) }
}