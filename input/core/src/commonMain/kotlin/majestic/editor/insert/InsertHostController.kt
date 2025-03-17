package majestic.editor.insert

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class InsertHostController(private val _inserts: MutableList<Insert> = mutableListOf()) {
    var selected by mutableStateOf(_inserts.firstOrNull())

    fun select(it: Insert) {
        selected = it
    }

}