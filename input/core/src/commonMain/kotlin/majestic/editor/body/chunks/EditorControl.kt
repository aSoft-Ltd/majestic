package majestic.editor.body.chunks

import majestic.editor.insert.InsertHostController

data class EditorControl(
    val actions: InsertHostController,
    val dropDowns: InsertHostController
)
