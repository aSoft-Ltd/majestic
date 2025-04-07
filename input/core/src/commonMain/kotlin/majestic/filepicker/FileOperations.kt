package majestic.filepicker

import androidx.compose.ui.graphics.painter.BitmapPainter

interface FileOperations {
    suspend fun getBitMap(uri: String): BitmapPainter
    suspend fun getBytes(uri: String): ByteArray
}