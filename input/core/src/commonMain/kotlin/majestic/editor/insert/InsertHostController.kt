package majestic.editor.insert

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class InsertHostController(private val _inserts: MutableList<Insert> = mutableListOf()) {
    val inserts: List<Insert> get() = _inserts
    var selected by mutableStateOf(_inserts.firstOrNull())

    fun select(it: Insert) {
        selected = it
    }

    fun updateInserts(inserts: List<Insert>) {
        val currentSelection = selected

        _inserts.clear()
        _inserts.addAll(inserts)

        if (inserts.any { it.name == currentSelection?.name }) {
            selected = currentSelection
        } else if (inserts.isNotEmpty()) {
            selected = inserts.first()
        }
    }
}