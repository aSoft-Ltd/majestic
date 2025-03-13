package majestic.editor.insert

import androidx.compose.runtime.mutableStateOf

data class InsertHostController(val inserts: List<Insert>) {
    val selected = mutableStateOf(inserts.first())

    fun select(it: Insert) {
        selected.value = it
    }
}
