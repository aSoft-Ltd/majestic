package majestic.editor.body

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Paragraph

class EditorBodyController(
    val chunks: SnapshotStateList<Chunk> = mutableStateListOf()
) {
    fun addHeading(level: Int) {
        chunks.add(Heading("", level))
    }

    fun addParagraph() {
        chunks.add(Paragraph(""))
    }

    fun remove(chunk: Chunk) {
        chunks.remove(chunk)
    }
}