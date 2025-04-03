package majestic.editor.body.chunks

import majestic.editor.body.chunks.lists.Items

data class Lists(
    override val uid: Int,
    val list: Items
) : Chunk {
    override fun copy(uid: Int) = copy(uid = uid, list = list)
}