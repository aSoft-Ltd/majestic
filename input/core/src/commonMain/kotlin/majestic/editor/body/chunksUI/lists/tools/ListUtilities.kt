package majestic.editor.body.chunksUI.lists.tools

import androidx.compose.ui.text.input.TextFieldValue
import majestic.editor.body.chunks.OrderedList
import majestic.editor.body.chunks.UnorderedList
import majestic.editor.body.chunks.lists.BulletType
import majestic.editor.body.chunks.lists.NumberingType
import majestic.editor.body.chunks.lists.SimpleListItem
import majestic.editor.body.chunksUI.tools.EditorBodyController


object ListUtilities {

    fun getBulletText(type: BulletType): String = when (type) {
        BulletType.DOT -> "● "
        BulletType.ARROW -> "➤ "
        BulletType.DIAMOND -> "◆ "
    }

    // Get number prefix based on type and index
    fun getNumberText(type: NumberingType, index: Int): String = when (type) {
        NumberingType.NUMBERS -> "${index + 1}. "
        NumberingType.ROMAN -> "${toRoman(index + 1)}. "
        NumberingType.CAPITAL_ALPHABETS -> "${('A' + index)}. "
        NumberingType.LOWERCASE_ALPHABETS -> "${('a' + index)}. "
    }

    // Convert number to Roman numeral
    private fun toRoman(number: Int): String {
        if (number <= 0) return ""
        val romanValues = listOf(
            1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
            100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
            10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
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

    // Create a new item in an ordered list
    fun createNewOrderedItem(index: Int, list: OrderedList, controller: EditorBodyController) {
        val newItem = SimpleListItem("")
        list.items.add(index + 1, newItem)
        val chunkIndex = controller.chunks.indexOf(list)
        if (chunkIndex != -1) controller.chunks[chunkIndex] = list
    }

    // Create a new item in an unordered list
    fun createNewUnorderedItem(index: Int, list: UnorderedList, controller: EditorBodyController) {
        val newItem = SimpleListItem("")
        list.items.add(index + 1, newItem)
        val chunkIndex = controller.chunks.indexOf(list)
        if (chunkIndex != -1) controller.chunks[chunkIndex] = list
    }

    // Merge an ordered list item with the previous one
    fun mergeWithPreviousOrderedItem(
        index: Int,
        textField: TextFieldValue,
        numberText: String,
        list: OrderedList,
        controller: EditorBodyController
    ) {
        val prevItem = list.items[index - 1]
        val prevText = prevItem.text
        val currentText = textField.text.removePrefix(numberText)
        val mergedText = if (prevText.isEmpty()) currentText else if (currentText.isEmpty()) prevText else "$prevText\n$currentText"
        prevItem.text = mergedText
        list.items.removeAt(index)
        val chunkIndex = controller.chunks.indexOf(list)
        if (chunkIndex != -1) controller.chunks[chunkIndex] = list
    }

    // Merge an unordered list item with the previous one
    fun mergeWithPreviousUnorderedItem(
        index: Int,
        textField: TextFieldValue,
        bulletText: String,
        list: UnorderedList,
        controller: EditorBodyController
    ) {
        val prevItem = list.items[index - 1]
        val prevText = prevItem.text
        val currentText = textField.text.removePrefix(bulletText)
        val mergedText = if (prevText.isEmpty()) currentText else if (currentText.isEmpty()) prevText else "$prevText\n$currentText"
        prevItem.text = mergedText
        list.items.removeAt(index)
        val chunkIndex = controller.chunks.indexOf(list)
        if (chunkIndex != -1) controller.chunks[chunkIndex] = list
    }
}