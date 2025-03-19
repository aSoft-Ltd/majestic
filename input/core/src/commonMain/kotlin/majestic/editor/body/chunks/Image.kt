package majestic.editor.body.chunks


data class Image(
    override val uid: Int,
    val url: String?,
    val caption: String?
) : Chunk {
    override fun copy(uid: Int): Chunk = copy(uid = uid, url = url, caption = caption)
}