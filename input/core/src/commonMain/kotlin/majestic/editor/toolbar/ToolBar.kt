package majestic.editor.toolbar

import androidx.compose.runtime.Composable

data class ToolBar(val name: String, val content: @Composable () -> Unit)
