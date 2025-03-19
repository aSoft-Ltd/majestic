package majestic.editor.body.chunks


data class Image(
    override val uid: Int,
    var name:String
) : Chunk {
    override fun copy(uid: Int): Chunk = copy(uid = uid, name = name)
}