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

    // Get or create a FocusRequester for an item
    fun getFocusRequester(index: Int): FocusRequester = focusRequesters.getOrPut(index) {
        FocusRequester()
    }

    // Update the text value for an item
    fun updateTextValue(index: Int, value: TextFieldValue) {
        textValues[index] = value
    }

    // Get the current text value, initializing with default if absent
    fun getTextValue(index: Int, defaultText: String): TextFieldValue {
        return textValues[index] ?: TextFieldValue(defaultText, TextRange(defaultText.length))
    }

    // Set focus state for an item
    fun setFocus(index: Int, isFocused: Boolean) {
        isFocusedMap[index] = isFocused
    }

    // Check if an item is focused
    fun isFocused(index: Int): Boolean {
        return isFocusedMap[index] ?: false
    }

    // Request focus for an item
    fun requestFocus(index: Int) {
        focusTarget = index
    }

    // Clean up stale state for removed items
    fun cleanup(validIndices: Set<Int>) {
        val toRemove = textValues.keys.toList().filter { it !in validIndices }
        toRemove.forEach {
            textValues.remove(it)
            focusRequesters.remove(it)
            isFocusedMap.remove(it)
            println("ListController 7: Cleaning up index $it")
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
            println("ListController 8: Creating new item at index $index")
            true
        }

        event.type == KeyEventType.KeyUp && event.key == Key.Enter && event.isShiftPressed -> {
            insertLineBreak(index, textField)
            println("ListController 9: Inserting line break at index $index")
            true
        }
        // Backspace at start with previous item: Merge
        event.type == KeyEventType.KeyUp && event.key == Key.Backspace &&
                textField.selection.start <= prefixText.length &&
                textField.selection.end <= prefixText.length && index > 0 -> {
            println("ListController 10: Merging with previous item at index $index")
            mergeWithPrevious(index, textField, prefixText, list, controller)
            true
        }
        // Backspace at start of first item: Clear if prefix only
        event.type == KeyEventType.KeyUp && event.key == Key.Backspace &&
                textField.selection.start <= prefixText.length &&
                textField.selection.end <= prefixText.length && index == 0 -> {
            if (textField.text == prefixText || textField.text.isEmpty()) {
                println("ListController 11: Clearing text at index $index")
                updateTextValue(index, TextFieldValue("", TextRange(0)))
                true
            } else false
        }
        // Delete at start: Clear if prefix only
        event.type == KeyEventType.KeyUp && event.key == Key.Delete &&
                textField.selection.start <= prefixText.length -> {
            if (textField.text == prefixText || textField.text.isEmpty()) {
                println("ListController 12: Clearing text at index $index")
                updateTextValue(index, TextFieldValue("", TextRange(0)))
                true
            } else false
        }
        // Delete: Remove character at cursor
        event.type == KeyEventType.KeyUp && event.key == Key.Delete -> {
            val cursorPos = textField.selection.start
            if (cursorPos < textField.text.length) {
                val newText = textField.text.substring(0, cursorPos) + textField.text.substring(cursorPos + 1)
                println("ListController 13: Deleting character at index $index")
                updateTextValue(index, TextFieldValue(newText, TextRange(cursorPos)))
                true
            } else false
        }

        else -> false
    }

    // Insert a line break in the current item
    private fun insertLineBreak(index: Int, textField: TextFieldValue) {
        val newText = if (textField.text.isEmpty()) "\n" else "${textField.text}\n"
        println("ListController 14: Inserting line break at index $index")
        updateTextValue(index, TextFieldValue(newText, TextRange(newText.length)))
    }

    // Adjust text to ensure it starts with the prefix
    fun adjustTextWithPrefix(newText: String, prefix: String): String = when {
        newText.isEmpty() -> ""
        !newText.startsWith(prefix) -> {
            val trimmed = newText.trimStart()
            println("ListController 15: Adjusting text to start with prefix $prefix")
            if (trimmed.isEmpty()) "" else "$prefix$trimmed"
        }

        else -> newText
    }

    // Update all items with current prefixes
    fun updateAllItems(list: T, controller: EditorBodyController, getPrefixFor: (Int) -> String) {
        list.items.forEachIndexed { index, item ->
            val prefix = getPrefixFor(index)
            if (!textValues.containsKey(index)) {
//                println("ListController 16: Initializing text value for index $index")
                updateTextValue(index, TextFieldValue(item.text, TextRange(item.text.length)))
            }
            if (item.text.isNotEmpty() && !item.text.startsWith(prefix)) {
                item.text = "$prefix${item.text}"
                println("ListController 17: Updating text for index $index to ${item.text}")
                updateTextValue(index, TextFieldValue(item.text, TextRange(item.text.length)))
            }
        }
        val index = controller.chunks.indexOf(list)
//        println("ListController 18: Updating list at index $index")
        if (index != -1) controller.chunks[index] = list
//        println("ListController 19: List updated at index $index")
    }
}