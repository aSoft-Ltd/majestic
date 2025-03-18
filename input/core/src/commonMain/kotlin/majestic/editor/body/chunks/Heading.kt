package majestic.editor.body.chunks

data class Heading(
    override val uid: Int,
    var text: String,
    var level: Int
) : Chunk {
    override fun copy(uid: Int) = copy(uid = uid, text = text, level = level)
}