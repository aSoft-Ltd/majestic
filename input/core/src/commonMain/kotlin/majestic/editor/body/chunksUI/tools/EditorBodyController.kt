package majestic.editor.body.chunksUI.tools

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Image
import majestic.editor.body.chunks.Lists
import majestic.editor.body.chunks.Paragraph
import majestic.editor.body.chunks.lists.Items
import majestic.editor.body.chunks.lists.Type

class EditorBodyController(val chunks: SnapshotStateList<Chunk> = mutableStateListOf()) {

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
            Lists(
                uid = getNextId(),
                list = Items(
                    type = Type.Ordered.Numbers,
                    items = mutableListOf("")
                )
            )
        )
    }

    fun addUnorderedList() {
        chunks.add(
            Lists(
                uid = getNextId(),
                list = Items(
                    type = Type.UnOrdered.Bullet,
                    items = mutableListOf("")
                )
            )
        )
    }

    fun addListItem(chunk: Lists){
        chunk.list.items.add("")

    }
   fun removeListItem(chunk: Lists, index: Int){
        chunk.list.items.removeAt(index)
    }


    fun changeToOrderedList(chunk: Chunk) {
        if (chunk !is Lists) return

        val newType = if (chunk.list.type is Type.Ordered) {
            Type.Ordered.Numbers
        } else {
            Type.Ordered.Numbers
        }

        val newChunk = chunk.copy(list = chunk.list.copy(type = newType))
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = newChunk
        }
    }

    fun changeToUnorderedList(chunk: Chunk) {
        if (chunk !is Lists) return

        val newType = if (chunk.list.type is Type.UnOrdered) {
            Type.UnOrdered.Bullet
        } else {
            Type.UnOrdered.Bullet
        }

        val newChunk = chunk.copy(list = chunk.list.copy(type = newType))
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = newChunk
        }

    }

    fun changeUnorderedType(chunk: Chunk, chosenOption:Type.UnOrdered) {
        if (chunk is Lists && chunk.list.type is Type.UnOrdered) {
            val newType =  if (chosenOption == Type.UnOrdered.Arrow){
                 Type.UnOrdered.Arrow
            } else if (chosenOption == Type.UnOrdered.Diamond){
                Type.UnOrdered.Diamond
            } else{
                Type.UnOrdered.Bullet
            }
            val newChunk = chunk.copy(list = chunk.list.copy(type = newType))
            val index = chunks.indexOf(chunk)
            if (index != -1) {
                chunks[index] = newChunk
            }
        }
    }

    fun changeOrderedType(chunk: Chunk, chosenOption: Type.Ordered) {
        if (chunk !is Lists || chunk.list.type !is Type.Ordered) return

        val currentType = chunk.list.type
        val newType = when (chosenOption) {
            Type.Ordered.Numbers -> Type.Ordered.Numbers
            is Type.Ordered.Alphabetic -> {
                when (currentType) {
                    is Type.Ordered.Alphabetic -> Type.Ordered.Alphabetic(caps = !currentType.caps)
                    is Type.Ordered.Roman -> Type.Ordered.Alphabetic(caps = currentType.caps)
                    Type.Ordered.Numbers -> Type.Ordered.Alphabetic(caps = chosenOption.caps)
                }
            }
            is Type.Ordered.Roman -> {
                when (currentType) {
                    is Type.Ordered.Roman -> Type.Ordered.Roman(caps = !currentType.caps)
                    is Type.Ordered.Alphabetic -> Type.Ordered.Roman(caps = currentType.caps)
                    Type.Ordered.Numbers -> Type.Ordered.Roman(caps = chosenOption.caps)
                }
            }
        }

        val newChunk = chunk.copy(list = chunk.list.copy(type = newType))
        val index = chunks.indexOf(chunk)
        if (index != -1) chunks[index] = newChunk
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
            is Lists -> Heading(uid = uid, text = chunk.list.items.joinToString(" "), level = level)
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
            is Lists -> Paragraph(uid = uid, text = chunk.list.items.joinToString(" "))
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
            is Lists -> if (chunk.list.items.joinToString(" ").isEmpty()) Image(uid = uid, caption = null, uri = "") else chunk
        }
    }
    fun changeToList(chunk: Chunk): Chunk {
        val uid = getNextId()
        return when (chunk) {
            is Heading -> if (chunk.text.isEmpty()) Lists(uid = uid, list = Items(type = Type.UnOrdered.Bullet, items = mutableListOf(""))) else chunk
            is Paragraph -> if (chunk.text.isEmpty()) Lists(uid = uid, list = Items(type = Type.UnOrdered.Bullet, items = mutableListOf(""))) else chunk
            is Image -> if (chunk.caption.isNullOrEmpty()) Lists(uid = uid, list = Items(type = Type.UnOrdered.Bullet, items = mutableListOf(""))) else chunk
            is Lists -> chunk
        }
    }

    fun duplicate(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        chunks.add(index + 1, chunk.copy(getNextId()))
    }
}