package majestic.editor.body.chunksUI

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Image
import majestic.editor.body.chunks.Paragraph


class EditorBodyController(
    val chunks: SnapshotStateList<Chunk> = mutableStateListOf()
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
                getNextId(),
                url = null,
                caption = null
            )
        )
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
        }
        val index = chunks.indexOf(chunk)
        chunks.remove(chunk)
        chunks.add(index, c)
    }

    fun changeToImage(chunk: Chunk): Chunk {
        val uid = getNextId()

        return when (chunk) {
            is Heading -> if (chunk.text.isEmpty()) Image(
                uid = uid, chunk.text,
                caption = null
            ) else chunk

            is Paragraph -> if (chunk.text.isEmpty()) Image(
                uid = uid, chunk.text,
                caption = null
            ) else chunk

            is Image -> chunk
        }
    }

    fun onClick() {
        println("clicked")

    }

    fun onDrop() {
        println("dropped")

    }

    fun duplicate(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        chunks.add(index + 1, chunk.copy(getNextId()))
    }
}