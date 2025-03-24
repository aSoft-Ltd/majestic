package majestic.editor.body.chunks.uploads

interface ImageUpload {
    suspend fun imageUpload(image: ByteArray): String
    suspend fun discardImage(url: String): Boolean

    fun pickImage(onImagePicked: (ByteArray) -> Unit, onPreview: (ByteArray) -> Unit)
}