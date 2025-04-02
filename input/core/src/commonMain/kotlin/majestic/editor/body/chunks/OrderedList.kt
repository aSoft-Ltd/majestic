package majestic.editor.body.chunks

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import majestic.editor.body.chunks.lists.ListChunk
import majestic.editor.body.chunks.lists.NumberingType
import majestic.editor.body.chunks.lists.SimpleListItem

data class OrderedList(
    override val uid: Int,
    override val items: MutableList<SimpleListItem> = mutableStateListOf(SimpleListItem("")),
    var numberingType: NumberingType = NumberingType.NUMBERS
) : Chunk, ListChunk {
    override fun copy(uid: Int): Chunk = OrderedList(
        uid,
        items.map { SimpleListItem(it.text) }.toMutableStateList(),
        numberingType
    )
}