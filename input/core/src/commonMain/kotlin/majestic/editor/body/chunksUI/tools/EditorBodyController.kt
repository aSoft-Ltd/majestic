package majestic.editor.body.chunksUI.tools

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kiota.FileManager
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Image
import majestic.editor.body.chunks.List
import majestic.editor.body.chunks.Paragraph
import majestic.editor.body.chunks.lists.Type

class EditorBodyController(
    val chunks: SnapshotStateList<Chunk> = mutableStateListOf(),
    val files: FileManager
) {
    private fun getNextId() = (chunks.maxOfOrNull { it.uid } ?: 0) + 1

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

    fun addOrderedList() {
        chunks.add(
            List(
                uid = getNextId(),
                type = Type.Ordered.Numbers,
                list = mutableStateListOf("")
            )
        )
    }

    fun addUnorderedList() {
        chunks.add(
            List(
                uid = getNextId(),
                type = Type.UnOrdered.Bullet,
                list = mutableStateListOf("")
            )
        )
    }

    fun addListItem(chunk: List) {
        chunk.list.add("")
    }

    fun removeListItem(chunk: List, index: Int) {
        chunk.list.removeAt(index)
    }

    fun changeToOrderedList(chunk: Chunk) {
        val uid = getNextId()
        val newChunk = when (chunk) {
            is Heading -> List(uid = uid, type = Type.Ordered.Numbers, list = mutableStateListOf(chunk.text))
            is Paragraph -> List(uid = uid, type = Type.Ordered.Numbers, list = mutableStateListOf(chunk.text))
            is Image -> List(uid = uid, type = Type.Ordered.Numbers, list = mutableStateListOf(chunk.caption ?: ""))
            is List -> chunk.copy(type = Type.Ordered.Numbers)
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, newChunk)
    }

    fun changeToUnorderedList(chunk: Chunk) {
        val uid = getNextId()
        val newChunk = when (chunk) {
            is Heading -> List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf(chunk.text))
            is Paragraph -> List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf(chunk.text))
            is Image -> List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf(chunk.caption ?: ""))
            is List -> chunk.copy(type = Type.UnOrdered.Bullet)
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, newChunk)
    }

    fun changeUnorderedType(chunk: List, chosenOption: Type.UnOrdered) {
        if (chunk !is List || chunk.type !is Type.UnOrdered) return

        val newType = when (chosenOption) {
            Type.UnOrdered.Arrow -> Type.UnOrdered.Arrow
            Type.UnOrdered.Diamond -> Type.UnOrdered.Diamond
            Type.UnOrdered.Bullet -> Type.UnOrdered.Bullet
        }

        val newChunk = chunk.copy(type = newType)
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = newChunk
        }
    }

    fun changeOrderedType(chunk: List, chosenOption: Type.Ordered) {
        if (chunk.type !is Type.Ordered) return

        val newType = when (chosenOption) {
            Type.Ordered.Numbers -> Type.Ordered.Numbers
            is Type.Ordered.Alphabetic -> Type.Ordered.Alphabetic(caps = chosenOption.caps)
            is Type.Ordered.Roman -> Type.Ordered.Roman(caps = chosenOption.caps)
        }

        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = chunk.copy(type = newType)
        }
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
        val newChunk = when (chunk) {
            is Heading -> chunk.copy(uid = uid, text = chunk.text, level = level)
            is Paragraph -> Heading(uid = uid, text = chunk.text, level = level)
            is Image -> Heading(uid = uid, text = chunk.caption ?: "", level = level)
            is List -> Heading(uid = uid, text = chunk.list.joinToString(" "), level = level)
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, newChunk)
    }

    fun changeToParagraph(chunk: Chunk) {
        val uid = getNextId()
        val newChunk = when (chunk) {
            is Heading -> Paragraph(uid = uid, text = chunk.text)
            is Paragraph -> Paragraph(uid = uid, text = chunk.text)
            is Image -> Paragraph(uid = uid, text = chunk.caption ?: "")
            is List -> Paragraph(uid = uid, text = chunk.list.joinToString(" "))
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, newChunk)
    }

    fun changeToImage(chunk: Chunk) {
        val uid = getNextId()
        val newChunk = when (chunk) {
            is Heading -> Image(uid = uid, caption = chunk.text.ifEmpty { null }, uri = "")
            is Paragraph -> Image(uid = uid, caption = chunk.text.ifEmpty { null }, uri = "")
            is Image -> chunk
            is List -> Image(uid = uid, caption = chunk.list.joinToString(" ").ifEmpty { null }, uri = "")
        }
        if (newChunk == chunk) return
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, newChunk)
    }

    fun changeToList(chunk: Chunk): Chunk {
        val uid = getNextId()
        return when (chunk) {
            is Heading -> if (chunk.text.isEmpty()) List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf("")) else chunk
            is Paragraph -> if (chunk.text.isEmpty()) List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf("")) else chunk
            is Image -> if (chunk.caption.isNullOrEmpty()) List(uid = uid, type = Type.UnOrdered.Bullet, list = mutableStateListOf("")) else chunk
            is List -> chunk
        }
    }

    fun duplicate(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        chunks.add(index + 1, chunk.copy(getNextId()))
    }
}