package majestic.editor.body.chunks

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import majestic.editor.body.chunks.lists.BulletType
import majestic.editor.body.chunks.lists.ListChunk
import majestic.editor.body.chunks.lists.SimpleListItem

data class UnorderedList(
    override val uid: Int,
    override val items: MutableList<SimpleListItem> = mutableStateListOf(SimpleListItem("")),
    var bulletType: BulletType = BulletType.DOT
) : Chunk, ListChunk {
    override fun copy(uid: Int): Chunk = UnorderedList(
        uid,
        items.map { SimpleListItem(it.text) }.toMutableStateList(),
        bulletType
    )
}