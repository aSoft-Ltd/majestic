package majestic.editor.body.chunksUI

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Image
import majestic.editor.body.chunks.OrderedList
import majestic.editor.body.chunks.Paragraph
import majestic.editor.body.chunks.UnorderedList
import majestic.editor.body.chunks.lists.BulletType
import majestic.editor.body.chunks.lists.NumberingType
import majestic.editor.body.chunks.lists.SimpleListItem

class EditorBodyController(val chunks: SnapshotStateList<Chunk> = mutableStateListOf()) {

    private fun getNextId() = (chunks.maxOfOrNull { it.uid } ?: 0) + 1
    private val bulletTypeMap = mutableMapOf<UnorderedList, BulletType>()
    private val numberingTypeMap = mutableMapOf<OrderedList, NumberingType>()

    fun getBulletType(list: UnorderedList): BulletType? {
        return bulletTypeMap[list]
    }

    fun getNumberingType(list: OrderedList): NumberingType? {
        return numberingTypeMap[list]
    }

    fun addHeading(level: Int) {
        chunks.add(Heading(getNextId(), "", level))
    }

    fun addParagraph() {
        chunks.add(Paragraph(getNextId(), ""))
    }

    fun addImage() {
        chunks.add(
            Image(
                uid = getNextId(),
                caption = null,
                uri = ""
            )
        )
    }

    fun addOrderedList(numberingType: NumberingType = NumberingType.NUMBERS) {
        chunks.add(OrderedList(getNextId(), numberingType = numberingType))
    }

    fun addUnorderedList(bulletType: BulletType = BulletType.DOT) {
        chunks.add(UnorderedList(getNextId(), bulletType = bulletType))
    }

    fun changeToOrderedList(chunk: Chunk, numberingType: NumberingType = NumberingType.NUMBERS) {
        val uid = getNextId()
        val c = when (chunk) {
            is Heading -> OrderedList(uid, mutableStateListOf(SimpleListItem(chunk.text)), numberingType)
            is Paragraph -> OrderedList(uid, mutableStateListOf(SimpleListItem(chunk.text)), numberingType)
            is Image -> OrderedList(uid, mutableStateListOf(SimpleListItem(chunk.caption ?: "")), numberingType)
            is OrderedList -> chunk.apply { this.numberingType = numberingType }
            is UnorderedList -> OrderedList(uid, chunk.items.toMutableStateList(), numberingType)
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, c)
    }

    fun changeToUnorderedList(chunk: Chunk, bulletType: BulletType = BulletType.DOT) {
        val uid = getNextId()
        val c = when (chunk) {
            is Heading -> UnorderedList(uid, mutableStateListOf(SimpleListItem(chunk.text)), bulletType)
            is Paragraph -> UnorderedList(uid, mutableStateListOf(SimpleListItem(chunk.text)), bulletType)
            is Image -> UnorderedList(uid, mutableStateListOf(SimpleListItem(chunk.caption ?: "")), bulletType)
            is OrderedList -> UnorderedList(uid, chunk.items.toMutableStateList(), bulletType)
            is UnorderedList -> chunk.apply { this.bulletType = bulletType }
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, c)
    }

    fun changeBulletType(chunk: UnorderedList, bulletType: BulletType) {
        chunk.bulletType = bulletType
    }

    fun changeNumberingType(chunk: OrderedList, numberingType: NumberingType) {
        chunk.numberingType = numberingType
    }

    fun remove(chunk: Chunk) {
        chunks.remove(chunk)
    }

    fun moveUp(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        if (index > 0) {
            chunks.remove(chunk)
            chunks.add(index - 1, chunk)
        }
    }

    fun moveToTop(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        if (index > 0) {
            chunks.remove(chunk)
            chunks.add(0, chunk)
        }
    }

    fun moveToBottom(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        if (index < chunks.size - 1) {
            chunks.remove(chunk)
            chunks.add(chunks.size, chunk)
        }
    }

    fun moveDown(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        if (index < chunks.size - 1) {
            chunks.remove(chunk)
            chunks.add(index + 1, chunk)
        }
    }

    fun changeToHeading(chunk: Chunk, level: Int) {
        val uid = getNextId()
        val c = when (chunk) {
            is Heading -> chunk.copy(uid = uid, text = chunk.text, level = level)
            is Paragraph -> Heading(uid = uid, text = chunk.text, level = level)
            is Image -> Heading(uid = uid, text = chunk.caption ?: "", level = level)
            is OrderedList -> Heading(uid = uid, text = chunk.items.joinToString(" ") { it.text }, level = level)
            is UnorderedList -> Heading(uid = uid, text = chunk.items.joinToString(" ") { it.text }, level = level)
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, c)
    }

    fun changeToParagraph(chunk: Chunk) {
        val uid = getNextId()
        val c = when (chunk) {
            is Heading -> Paragraph(uid = uid, text = chunk.text)
            is Paragraph -> Paragraph(uid = uid, text = chunk.text)
            is Image -> Paragraph(uid = uid, text = chunk.caption ?: "")
            is OrderedList -> Paragraph(uid = uid, text = chunk.items.joinToString(" ") { it.text })
            is UnorderedList -> Paragraph(uid = uid, text = chunk.items.joinToString(" ") { it.text })
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, c)
    }

    fun changeToImage(chunk: Chunk): Chunk {
        val uid = getNextId()
        return when (chunk) {
            is Heading -> if (chunk.text.isEmpty()) Image(uid = uid, caption = null, uri = chunk.text) else chunk
            is Paragraph -> if (chunk.text.isEmpty()) Image(uid = uid, caption = null, uri = chunk.text) else chunk
            is Image -> chunk
            is OrderedList -> if (chunk.items.all { it.text.isEmpty() }) Image(uid = uid, caption = null, uri = "") else chunk
            is UnorderedList -> if (chunk.items.all { it.text.isEmpty() }) Image(uid = uid, caption = null, uri = "") else chunk
        }
    }

    fun duplicate(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        chunks.add(index + 1, chunk.copy(getNextId()))
    }
}