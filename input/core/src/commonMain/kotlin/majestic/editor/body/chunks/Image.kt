package majestic.editor.body.chunks

import androidx.compose.ui.graphics.painter.BitmapPainter


data class Image(
    override val uid: Int,
    val uri: String?,
    val caption: String?,
    val painter: BitmapPainter? = null,
    val isPreviewVisible: Boolean = true
) : Chunk {
    override fun copy(uid: Int): Chunk = copy(uid = uid, uri = uri, caption = caption)

    // Function to create a copy with updated painter or preview visibility
    fun copyWithPainter(newPainter: BitmapPainter?) = copy(painter = newPainter)
    fun copyWithPreviewVisible(visible: Boolean) = copy(isPreviewVisible = visible)
}