package majestic.editor.body.chunks


data class Image(
    override val uid: Int,
    val uri: String?,
    val caption: String?,
) : Chunk {
    override fun copy(uid: Int): Chunk = copy(uid = uid, uri = uri, caption = caption)
}