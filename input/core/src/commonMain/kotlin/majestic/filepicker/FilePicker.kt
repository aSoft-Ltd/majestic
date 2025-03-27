package majestic.filepicker

import androidx.compose.ui.graphics.painter.BitmapPainter

interface FilePicker {
    data class Config(
        val allowedFileTypes: List<FileType> = listOf(FileType.ALL),
        val maxFiles: Int = 1
    )

    fun hasPermission(): Boolean

    suspend fun requestPermission(): Boolean

    suspend fun pickFiles(config: Config = Config()): List<FileInfo>

    suspend fun searchFiles(
        query: String,
        path: String? = null,
        fileTypes: List<FileType> = listOf(FileType.ALL),
    ): List<FileInfo>

    fun fileExists(path: String): Boolean
    suspend fun getBitMap(uri: String): BitmapPainter
    suspend fun getBytes(uri: String): ByteArray
}