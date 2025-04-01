package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import majestic.editor.BorderlessInput
import majestic.editor.body.chunks.UnorderedList
import majestic.editor.body.chunks.lists.BulletType
import majestic.editor.body.chunks.lists.SimpleListItem
import majestic.editor.toolbar.EditorColors

@Composable
fun UnorderedChunk(
    list: UnorderedList,
    colors: EditorColors,
    labels: Labels,
    controller: EditorBodyController,
    modifier: Modifier = Modifier
) {
    println("DEBUG: UnorderedChunk composing with ${list.items.size} items")

    val listItems = remember {
        println("DEBUG: Creating mutable state list")
        list.items.toMutableStateList()
    }

    // Get the current bullet type from the controller
    val bulletType by remember { mutableStateOf(controller.getBulletType(list) ?: list.bulletType) }

    // Update the bullet type in the list when it changes
    LaunchedEffect(bulletType) {
        println("DEBUG: Bullet type changed to $bulletType")
        list.bulletType = bulletType
    }

    LaunchedEffect(listItems.size) {
        println("DEBUG: LaunchedEffect triggered with list size: ${list.items.size}")
        list.items.clear()
        list.items.addAll(listItems)
        println("DEBUG: Original list updated, now contains ${list.items.size} items")
    }

    Box(
        modifier = modifier
            .background(color = colors.background, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val focusRequesters = remember {
                println("DEBUG: Creating focus requesters map")
                mutableStateMapOf<Int, FocusRequester>()
            }
            val textValues = remember {
                println("DEBUG: Creating text values map")
                mutableStateMapOf<Int, TextFieldValue>()
            }
            val isFocusedMap = remember {
                println("DEBUG: Creating isFocused map")
                mutableStateMapOf<Int, Boolean>()
            }
            var focusTarget by remember {
                println("DEBUG: Creating focus target state")
                mutableStateOf<Int?>(null)
            }

            val getBulletText = { type: BulletType ->
                val baseText = when (type) {
                    BulletType.DOT -> "●" // Updated to smaller bullet as per previous requirement
                    BulletType.ARROW -> "➤ "
                    BulletType.DIAMOND -> "◆ "
                }
                baseText
            }

            LaunchedEffect(bulletType) {
                val newBulletText = getBulletText(bulletType)
                listItems.forEachIndexed { index, item ->
                    val oldBulletText = getBulletText(list.bulletType)
                    if (item.text.startsWith(oldBulletText)) {
                        val content = item.text.removePrefix(oldBulletText)
                        item.text = "$newBulletText$content"
                        textValues[index] = TextFieldValue(
                            text = item.text,
                            selection = TextRange(item.text.length)
                        )
                    }
                }
            }

            remember(listItems.size) {
                listItems.forEachIndexed { index, item ->
                    val bulletText = getBulletText(bulletType)
                    if (item.text.isNotEmpty() && !item.text.startsWith(bulletText)) {
                        item.text = "$bulletText${item.text}"
                        textValues[index] = TextFieldValue(item.text, TextRange(item.text.length))
                    } else if (item.text.isEmpty()) {
                        textValues[index] = TextFieldValue("", TextRange(0))
                    }
                }
            }

            listItems.forEachIndexed { index, item ->

                key(index) {

                    val focusRequester = focusRequesters.getOrPut(index) {
                        FocusRequester()
                    }

                    if (!textValues.containsKey(index)) {
                        textValues[index] = TextFieldValue(item.text, TextRange(item.text.length))
                    }

                    val textField = textValues[index] ?: TextFieldValue(item.text)
                    val isFocused = isFocusedMap[index] ?: false

                    LaunchedEffect(textField.text) {
                        item.text = textField.text
                    }

                    LaunchedEffect(focusTarget) {
                        if (focusTarget == index) {
                            println("DEBUG: About to request focus for index $index")
                            delay(50)
                            try {
                                focusRequester.requestFocus()
                                println("DEBUG: Focus requested successfully for index $index")
                            } catch (e: Exception) {
                                println("DEBUG: Error requesting focus: ${e.message}")
                            }
                            focusTarget = null
                            println("DEBUG: Focus target reset to null")
                        }
                    }

                    val bulletText = getBulletText(bulletType)
                    val displayText = if (isFocused) {
                        if (textField.text.isEmpty()) bulletText else textField.text
                    } else {
                        if (textField.text.isEmpty()) "" else textField.text
                    }

                    BorderlessInput(
                        value = displayText,
                        onChange = { newText ->
                            println("DEBUG: Text changed in BorderlessInput for index $index from '${textField.text}' to '$newText'")
                            val adjustedText = if (newText.isEmpty()) {
                                ""
                            } else if (!newText.startsWith(bulletText)) {
                                val trimmed = newText.trimStart()
                                if (trimmed.isEmpty()) "" else "$bulletText$trimmed"
                            } else {
                                newText
                            }
                            textValues[index] = TextFieldValue(
                                text = adjustedText,
                                selection = TextRange(adjustedText.length)
                            )
                            println("DEBUG: Updated TextFieldValue with selection: ${textField.selection}")
                        },
                        hint = "${bulletText}Add text",
                        colors = colors,
                        singleLine = false,
                        style = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState ->
                                println("DEBUG: Focus changed for index $index: ${focusState.isFocused}")
                                isFocusedMap[index] = focusState.isFocused
                                if (focusState.isFocused && textField.text.isEmpty()) {
                                    textValues[index] = TextFieldValue(
                                        text = bulletText,
                                        selection = TextRange(bulletText.length)
                                    )
                                }
                            }
                            .onKeyEvent { event ->
                                println("DEBUG: Key event detected: ${event.key}, type: ${event.type}, shift pressed: ${event.isShiftPressed}")
                                when {
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Enter &&
                                            !event.isShiftPressed -> {
                                        println("DEBUG: Enter key pressed (KeyUp) at index $index")
                                        val newItem = SimpleListItem("")
                                        println("DEBUG: Adding new item after index $index")
                                        listItems.add(index + 1, newItem)
                                        textValues[index + 1] = TextFieldValue(
                                            text = bulletText,
                                            selection = TextRange(bulletText.length)
                                        )
                                        println("DEBUG: Setting focus target to index ${index + 1}")
                                        focusTarget = index + 1
                                        true
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Enter &&
                                            event.isShiftPressed -> {
                                        println("DEBUG: Shift + Enter pressed at index $index")
                                        val currentText = textField.text
                                        val newText = if (currentText.isEmpty()) "\n" else "$currentText\n"
                                        textValues[index] = TextFieldValue(
                                            text = newText,
                                            selection = TextRange(newText.length)
                                        )
                                        true
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Backspace &&
                                            textField.selection.start <= bulletText.length &&
                                            textField.selection.end <= bulletText.length &&
                                            index > 0 -> {
                                        println("DEBUG: Backspace at or before bullet for index $index")
                                        val prevItem = listItems[index - 1]
                                        val prevText = prevItem.text
                                        val currentText = textField.text.removePrefix(bulletText)

                                        println("DEBUG: Previous item text: '$prevText'")
                                        println("DEBUG: Current item text after bullet removal: '$currentText'")

                                        val mergedText = if (prevText.isEmpty()) {
                                            currentText
                                        } else if (currentText.isEmpty()) {
                                            prevText
                                        } else {
                                            "$prevText\n$currentText"
                                        }

                                        println("DEBUG: Merged text: '$mergedText'")
                                        listItems[index - 1] = SimpleListItem(mergedText)
                                        val cursorPosition = prevText.length + if (prevText.isEmpty()) 0 else 1
                                        println("DEBUG: Setting cursor position to $cursorPosition in merged item")
                                        textValues[index - 1] = TextFieldValue(
                                            text = mergedText,
                                            selection = TextRange(cursorPosition)
                                        )
                                        println("DEBUG: Removing item at index $index")
                                        listItems.removeAt(index)
                                        println("DEBUG: Setting focus target to index ${index - 1}")
                                        focusTarget = index - 1
                                        true
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Backspace &&
                                            textField.selection.start <= bulletText.length &&
                                            textField.selection.end <= bulletText.length &&
                                            index == 0 -> {
                                        println("DEBUG: Backspace at or before bullet on first item")
                                        if (textField.text == bulletText || textField.text.isEmpty()) {
                                            println("DEBUG: Text is bullet or empty, deleting bullet")
                                            textValues[index] = TextFieldValue("", TextRange(0))
                                            true
                                        } else {
                                            println("DEBUG: Text has content beyond bullet, allowing backspace")
                                            false
                                        }
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Delete &&
                                            textField.selection.start <= bulletText.length -> {
                                        println("DEBUG: Delete key pressed at or before bullet for index $index")
                                        if (textField.text == bulletText || textField.text.isEmpty()) {
                                            println("DEBUG: Text is bullet or empty, deleting bullet")
                                            textValues[index] = TextFieldValue("", TextRange(0))
                                            true
                                        } else {
                                            println("DEBUG: Text has content beyond bullet, allowing delete")
                                            false
                                        }
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Delete -> {
                                        println("DEBUG: Delete key pressed after bullet for index $index")
                                        val cursorPos = textField.selection.start
                                        if (cursorPos < textField.text.length) {
                                            val newText = textField.text.substring(0, cursorPos) +
                                                    textField.text.substring(cursorPos + 1)
                                            textValues[index] = TextFieldValue(
                                                text = newText,
                                                selection = TextRange(cursorPos)
                                            )
                                            true
                                        } else {
                                            false
                                        }
                                    }
                                    else -> {
                                        println("DEBUG: Unhandled key event, passing through")
                                        false
                                    }
                                }
                            }
                    )
                }
            }

            SideEffect {
                val validIndices = listItems.indices.toSet()
                val toRemove = textValues.keys.toList().filter { it !in validIndices }
                if (toRemove.isNotEmpty()) {
                    println("DEBUG: Cleaning up ${toRemove.size} removed items: $toRemove")
                    toRemove.forEach {
                        textValues.remove(it)
                        focusRequesters.remove(it)
                        isFocusedMap.remove(it)
                    }
                }
            }
        }
    }
}