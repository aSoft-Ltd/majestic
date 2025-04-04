package majestic.editor.body.chunks

import majestic.editor.body.chunks.lists.Type

data class List(
    override val uid: Int,
    val type: Type,
    val list: MutableList<String>
) : Chunk {
    override fun copy(uid: Int) = copy(uid = uid, type = type, list = list)
}