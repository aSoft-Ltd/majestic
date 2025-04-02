package majestic.editor.body.chunksUI.lists.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.lists.ListChunk
import majestic.editor.body.chunksUI.tools.EditorBodyController


class ListController<T> where T : ListChunk, T : Chunk {
    private val focusRequesters = mutableStateMapOf<Int, FocusRequester>()
    val textValues = mutableStateMapOf<Int, TextFieldValue>()
    private val isFocusedMap = mutableStateMapOf<Int, Boolean>()
    var focusTarget by mutableStateOf<Int?>(null)

    fun getFocusRequester(index: Int): FocusRequester = focusRequesters.getOrPut(index) {
        FocusRequester()
    }

    fun updateTextValue(index: Int, value: TextFieldValue) {
        textValues[index] = value
    }

    fun getTextValue(index: Int, defaultText: String): TextFieldValue {
        return textValues[index] ?: TextFieldValue(defaultText, TextRange(defaultText.length))
    }

    fun setFocus(index: Int, isFocused: Boolean) {
        isFocusedMap[index] = isFocused
    }

    fun isFocused(index: Int): Boolean {
        return isFocusedMap[index] ?: false
    }

    fun requestFocus(index: Int) {
        focusTarget = index
    }

    fun cleanup(validIndices: Set<Int>) {
        val toRemove = textValues.keys.toList().filter { it !in validIndices }
        toRemove.forEach {
            textValues.remove(it)
            focusRequesters.remove(it)
            isFocusedMap.remove(it)
        }
    }

    fun handleKeyEvent(
        event: KeyEvent,
        index: Int,
        textField: TextFieldValue,
        prefixText: String,
        list: T,
        controller: EditorBodyController,
        createNewItem: (Int, T, EditorBodyController) -> Unit,
        mergeWithPrevious: (Int, TextFieldValue, String, T, EditorBodyController) -> Unit
    ): Boolean = when {

        event.type == KeyEventType.KeyUp && event.key == Key.Enter && !event.isShiftPressed -> {
            createNewItem(index, list, controller)
            true
        }

        event.type == KeyEventType.KeyUp && event.key == Key.Enter && event.isShiftPressed -> {
            insertLineBreak(index, textField)
            true
        }

        event.type == KeyEventType.KeyUp && event.key == Key.Backspace &&
                textField.selection.start <= prefixText.length &&
                textField.selection.end <= prefixText.length && index > 0 -> {
            mergeWithPrevious(index, textField, prefixText, list, controller)
            true
        }
        event.type == KeyEventType.KeyUp && event.key == Key.Backspace &&
                textField.selection.start <= prefixText.length &&
                textField.selection.end <= prefixText.length && index == 0 -> {
            if (textField.text == prefixText || textField.text.isEmpty()) {
                updateTextValue(index, TextFieldValue("", TextRange(0)))
                true
            } else false
        }
        event.type == KeyEventType.KeyUp && event.key == Key.Delete &&
                textField.selection.start <= prefixText.length -> {
            if (textField.text == prefixText || textField.text.isEmpty()) {
                updateTextValue(index, TextFieldValue("", TextRange(0)))
                true
            } else false
        }

        event.type == KeyEventType.KeyUp && event.key == Key.Delete -> {
            val cursorPos = textField.selection.start
            if (cursorPos < textField.text.length) {
                val newText = textField.text.substring(0, cursorPos) + textField.text.substring(cursorPos + 1)
                updateTextValue(index, TextFieldValue(newText, TextRange(cursorPos)))
                true
            } else false
        }

        else -> false
    }

    private fun insertLineBreak(index: Int, textField: TextFieldValue) {
        val newText = if (textField.text.isEmpty()) "\n" else "${textField.text}\n"
        updateTextValue(index, TextFieldValue(newText, TextRange(newText.length)))
    }

    fun adjustTextWithPrefix(newText: String, prefix: String): String = when {
        newText.isEmpty() -> ""
        !newText.startsWith(prefix) -> {
            val trimmed = newText.trimStart()
            if (trimmed.isEmpty()) "" else "$prefix$trimmed"
        }

        else -> newText
    }

    fun updateAllItems(list: T, controller: EditorBodyController, getPrefixFor: (Int) -> String) {
        list.items.forEachIndexed { index, item ->
            val prefix = getPrefixFor(index)
            if (!textValues.containsKey(index)) {
                updateTextValue(index, TextFieldValue(item.text, TextRange(item.text.length)))
            }
            if (item.text.isNotEmpty() && !item.text.startsWith(prefix)) {
                item.text = "$prefix${item.text}"
                updateTextValue(index, TextFieldValue(item.text, TextRange(item.text.length)))
            }
        }
        val index = controller.chunks.indexOf(list)
        if (index != -1) controller.chunks[index] = list
    }
}