package majestic.editor.body.chunks.lists

import majestic.editor.body.chunks.Chunk

interface ListChunk {
    val items: MutableList<SimpleListItem>
    fun copy(uid: Int): Chunk
}