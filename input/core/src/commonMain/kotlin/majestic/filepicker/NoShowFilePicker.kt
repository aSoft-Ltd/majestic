package majestic.filepicker

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

class NoShowFilePicker : FilePicker {
    override fun hasPermission(): Boolean {
        return true
    }

    override suspend fun requestPermission(): Boolean {
        return true
    }

    override suspend fun pickFiles(config: FilePicker.Config): List<FileInfo> {
        return emptyList()
    }

    override suspend fun getBitMap(uri: String): BitmapPainter {
        return return BitmapPainter(
            image = ImageBitmap(1, 1),
            srcOffset = IntOffset.Zero,
            srcSize = IntSize(1, 1)
        )
    }

    override suspend fun getBytes(uri: String): ByteArray {
        return ByteArray(0)
    }
}