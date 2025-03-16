package majestic.editor.insert

import androidx.compose.runtime.Composable

data class Insert(val name: String, val content: @Composable () -> Unit)
