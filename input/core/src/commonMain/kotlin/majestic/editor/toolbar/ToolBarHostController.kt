package majestic.editor.toolbar

import androidx.compose.runtime.mutableStateOf

data class ToolBarHostController(val tabs: List<ToolBar>) {
    val selected = mutableStateOf(tabs.first())

    fun select(it: ToolBar) {
        selected.value = it
    }
}