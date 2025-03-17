package majestic.editor.body.chunkUI

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import majestic.editor.body.chunks.Chunk
import majestic.editor.body.chunks.EditorControl
import majestic.editor.body.chunks.Heading
import majestic.editor.body.chunks.Paragraph
import majestic.editor.insert.InsertHostController


class EditorBodyController(
    val chunks: SnapshotStateList<Chunk> = mutableStateListOf()
) {

    val actions: InsertHostController = InsertHostController()
    val dropDowns: InsertHostController = InsertHostController()


    val editorControl: EditorControl
        get() = EditorControl(actions, dropDowns)

    init {
        addHeading(1)
    }


    fun connectActionsController(controller: InsertHostController) {
        actions.updateInserts(controller.inserts)
    }

    fun connectDropDownsController(controller: InsertHostController) {
        dropDowns.updateInserts(controller.inserts)
    }

    fun addHeading(level: Int) {
        chunks.add(Heading("", level))
    }

    fun addParagraph() {
        chunks.add(Paragraph(""))
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

    fun moveDown(chunk: Chunk) {
        val index = chunks.indexOf(chunk)
        if (index < chunks.size - 1) {
            chunks.remove(chunk)
            chunks.add(index + 1, chunk)
        }
    }
}