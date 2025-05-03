package majestic.editor.body.chunks

sealed interface Chunk {
    val uid: Int
    fun copy(uid: Int): Chunk
}