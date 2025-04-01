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
import majestic.editor.body.chunks.OrderedList
import majestic.editor.body.chunks.lists.NumberingType
import majestic.editor.body.chunks.lists.SimpleListItem
import majestic.editor.toolbar.EditorColors

@Composable
fun OrderedChunk(
    list: OrderedList,
    colors: EditorColors,
    labels: Labels,
    controller: EditorBodyController,
    modifier: Modifier = Modifier
) {
    val listItems = remember { list.items.toMutableStateList() }

    val numberingType by remember { mutableStateOf(controller.getNumberingType(list) ?: list.numberingType) }

    LaunchedEffect(numberingType) {
        list.numberingType = numberingType
    }

    LaunchedEffect(listItems.size) {
        list.items.clear()
        list.items.addAll(listItems)
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
            val focusRequesters = remember { mutableStateMapOf<Int, FocusRequester>() }
            val textValues = remember { mutableStateMapOf<Int, TextFieldValue>() }
            val isFocusedMap = remember { mutableStateMapOf<Int, Boolean>() }
            var focusTarget by remember { mutableStateOf<Int?>(null) }

            val getNumberText = { type: NumberingType, index: Int ->
                when (type) {
                    NumberingType.NUMBERS -> "${index + 1}. "
                    NumberingType.ROMAN -> "${toRoman(index + 1)}. "
                    NumberingType.CAPITAL_ALPHABETS -> "${('A' + index)}. "
                    NumberingType.LOWERCASE_ALPHABETS -> "${('a' + index)}. "
                }
            }

            LaunchedEffect(numberingType, listItems.size) {
                listItems.forEachIndexed { index, item ->
                    val newNumberText = getNumberText(numberingType, index)
                    val oldNumberText = if (item.text.isNotEmpty()) {
                        val possiblePrefixes = listOf(
                            getNumberText(NumberingType.NUMBERS, index),
                            getNumberText(NumberingType.ROMAN, index),
                            getNumberText(NumberingType.CAPITAL_ALPHABETS, index)
                        )
                        possiblePrefixes.firstOrNull { item.text.startsWith(it) } ?: ""
                    } else {
                        ""
                    }
                    if (item.text.startsWith(oldNumberText)) {
                        val content = item.text.removePrefix(oldNumberText)
                        item.text = "$newNumberText$content"
                        textValues[index] = TextFieldValue(
                            text = item.text,
                            selection = TextRange(item.text.length)
                        )
                    }
                }
            }

            remember(listItems.size) {
                listItems.forEachIndexed { index, item ->
                    val numberText = getNumberText(numberingType, index)
                    if (item.text.isNotEmpty() && !item.text.startsWith(numberText)) {
                        val possiblePrefixes = listOf(
                            getNumberText(NumberingType.NUMBERS, index),
                            getNumberText(NumberingType.ROMAN, index),
                            getNumberText(NumberingType.CAPITAL_ALPHABETS, index)
                        )
                        val currentPrefix = possiblePrefixes.firstOrNull { item.text.startsWith(it) }
                        if (currentPrefix != null) {
                            item.text = item.text.removePrefix(currentPrefix)
                        }
                        item.text = "$numberText${item.text}"
                        textValues[index] = TextFieldValue(item.text, TextRange(item.text.length))
                    } else if (item.text.isEmpty()) {
                        textValues[index] = TextFieldValue("", TextRange(0))
                    }
                }
            }

            listItems.forEachIndexed { index, item ->
                key(index) {
                    val focusRequester = focusRequesters.getOrPut(index) { FocusRequester() }

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
                            delay(50)
                            try {
                                focusRequester.requestFocus()
                            } catch (e: Exception) {}
                            focusTarget = null
                        }
                    }

                    val numberText = getNumberText(numberingType, index)
                    val displayText = if (isFocused) {
                        if (textField.text.isEmpty()) numberText else textField.text
                    } else {
                        if (textField.text.isEmpty()) "" else textField.text
                    }

                    BorderlessInput(
                        value = displayText,
                        onChange = { newText ->
                            val adjustedText = if (newText.isEmpty()) {
                                ""
                            } else if (!newText.startsWith(numberText)) {
                                val trimmed = newText.trimStart()
                                if (trimmed.isEmpty()) "" else "$numberText$trimmed"
                            } else {
                                newText
                            }
                            textValues[index] = TextFieldValue(
                                text = adjustedText,
                                selection = TextRange(adjustedText.length)
                            )
                        },
                        hint = "${numberText}Add text",
                        colors = colors,
                        singleLine = false,
                        style = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState ->
                                isFocusedMap[index] = focusState.isFocused
                                if (focusState.isFocused && textField.text.isEmpty()) {
                                    textValues[index] = TextFieldValue(
                                        text = numberText,
                                        selection = TextRange(numberText.length)
                                    )
                                }
                            }
                            .onKeyEvent { event ->
                                when {
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Enter &&
                                            !event.isShiftPressed -> {
                                        val newItem = SimpleListItem("")
                                        listItems.add(index + 1, newItem)
                                        textValues[index + 1] = TextFieldValue(
                                            text = getNumberText(numberingType, index + 1),
                                            selection = TextRange(getNumberText(numberingType, index + 1).length)
                                        )
                                        focusTarget = index + 1
                                        true
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Enter &&
                                            event.isShiftPressed -> {
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
                                            textField.selection.start <= numberText.length &&
                                            textField.selection.end <= numberText.length &&
                                            index > 0 -> {
                                        val prevItem = listItems[index - 1]
                                        val prevText = prevItem.text
                                        val currentText = textField.text.removePrefix(numberText)

                                        val mergedText = if (prevText.isEmpty()) {
                                            currentText
                                        } else if (currentText.isEmpty()) {
                                            prevText
                                        } else {
                                            "$prevText\n$currentText"
                                        }

                                        listItems[index - 1] = SimpleListItem(mergedText)
                                        val cursorPosition = prevText.length + if (prevText.isEmpty()) 0 else 1
                                        textValues[index - 1] = TextFieldValue(
                                            text = mergedText,
                                            selection = TextRange(cursorPosition)
                                        )
                                        listItems.removeAt(index)
                                        focusTarget = index - 1
                                        true
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Backspace &&
                                            textField.selection.start <= numberText.length &&
                                            textField.selection.end <= numberText.length &&
                                            index == 0 -> {
                                        if (textField.text == numberText || textField.text.isEmpty()) {
                                            textValues[index] = TextFieldValue("", TextRange(0))
                                            true
                                        } else {
                                            false
                                        }
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Delete &&
                                            textField.selection.start <= numberText.length -> {
                                        if (textField.text == numberText || textField.text.isEmpty()) {
                                            textValues[index] = TextFieldValue("", TextRange(0))
                                            true
                                        } else {
                                            false
                                        }
                                    }
                                    event.type == KeyEventType.KeyUp &&
                                            event.key == Key.Delete -> {
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
                                    else -> false
                                }
                            }
                    )
                }
            }

            SideEffect {
                val validIndices = listItems.indices.toSet()
                val toRemove = textValues.keys.toList().filter { it !in validIndices }
                if (toRemove.isNotEmpty()) {
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

private fun toRoman(number: Int): String {
    if (number <= 0) return ""
    val romanValues = listOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )
    var result = ""
    var remaining = number
    for ((value, symbol) in romanValues) {
        while (remaining >= value) {
            result += symbol
            remaining -= value
        }
    }
    return result
}