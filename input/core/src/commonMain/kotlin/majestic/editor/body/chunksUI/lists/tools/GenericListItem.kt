package majestic.editor.body.chunksUI.lists.tools

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import majestic.editor.BorderlessInput
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.lists.ListChunk
import majestic.editor.body.chunks.lists.SimpleListItem
import majestic.editor.body.chunksUI.tools.EditorBodyController
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.toolbar.EditorColors

@Composable
fun <T> GenericListItem(
    item: SimpleListItem,
    index: Int,
    prefixText: String,
    label: Labels,
    listController: ListController<T>,
    list: T,
    controller: EditorBodyController,
    colors: EditorColors,
    modifier: Modifier = Modifier,
    createNewItem: (Int, T, EditorBodyController) -> Unit,
    mergeWithPrevious: (Int, TextFieldValue, String, T, EditorBodyController) -> Unit
) where T : ListChunk, T : Chunk {
    val focusRequester = listController.getFocusRequester(index)
    val textField = listController.getTextValue(index, item.text)
    val isFocused = listController.isFocused(index)

    // Sync item text with text field changes
    LaunchedEffect(textField.text) {
        item.text = textField.text
    }

    LaunchedEffect(listController.focusTarget) {
        if (listController.focusTarget == index) {
            delay(50)
            try {
                focusRequester.requestFocus()
            } catch (e: Exception) {
            }
            listController.focusTarget = null
        }
    }

    val displayText = if (isFocused) {
        if (textField.text.isEmpty()) prefixText else textField.text
    } else {
        if (textField.text.isEmpty()) "" else textField.text
    }

    BorderlessInput(
        value = displayText,
        onChange = { newText ->
            val adjustedText = listController.adjustTextWithPrefix(newText, prefixText)
            listController.updateTextValue(index, TextFieldValue(adjustedText, TextRange(adjustedText.length)))
        },
        hint = "$prefixText ${label.list}",
        colors = colors,
        singleLine = false,
        style = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                listController.setFocus(index, focusState.isFocused)
                if (focusState.isFocused && textField.text.isEmpty()) {
                    listController.updateTextValue(index, TextFieldValue(prefixText, TextRange(prefixText.length)))
                }
            }
            .onKeyEvent { event ->
                listController.handleKeyEvent(
                    event, index, textField, prefixText, list, controller, createNewItem, mergeWithPrevious
                )
            }
    )
}