package majestic.editor.body.chunks

data class Paragraph(
    override val uid: Int,
    var text: String
) : Chunk {
    override fun copy(uid: Int): Chunk = copy(uid = uid, text = text)
}