package majestic.editor.body.chunksUI

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.painter.BitmapPainter
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Image
import majestic.editor.body.chunks.Paragraph
import majestic.filepicker.FilePicker
import majestic.filepicker.FileType


class EditorBodyController(
    val chunks: SnapshotStateList<Chunk> = mutableStateListOf(),
    val filePicker: FilePicker
) {

    private fun getNextId() = (chunks.maxOfOrNull { it.uid } ?: 0) + 1

    fun addHeading(level: Int) {
        chunks.add(Heading(getNextId(), "", level))
    }

    fun addParagraph() {
        chunks.add(Paragraph(getNextId(), ""))
    }

    suspend fun pickImage(): BitmapPainter? {
        val files = filePicker.pickFiles( //if pick has permission then pick file, else
            config = FilePicker.Config(maxFiles = 1, allowedFileTypes = listOf(FileType.IMAGE))
        )
        return files.firstOrNull()?.let { fileInfo -> filePicker.getBitMap(fileInfo.path) }
    }


    fun attachImageToChunk(chunk: Image, painter: BitmapPainter) {
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = chunk.copy(
                painter = painter,
                uri = ""
            )
        }
    }

    fun addImage() {
        chunks.add(
            Image(
                uid = getNextId(),
                caption = null,
                uri = "",
                painter = null
            )
        )
    }

    fun togglePreview(chunk: Image) {
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = chunk.copyWithPreviewVisible(!chunk.isPreviewVisible)
        }
    }

    fun discardImage(chunk: Image) {
        val index = chunks.indexOf(chunk)
        if (index != -1) {
            chunks[index] = chunk.copyWithPainter(null)
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

    fun clickToUpload(url: String) {
        println("Uploaded image URL: $url")
    }

    fun duplicate(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        chunks.add(index + 1, chunk.copy(getNextId()))
    }
}