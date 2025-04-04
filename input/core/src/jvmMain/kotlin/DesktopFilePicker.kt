package majestic.filepicker

import androidx.compose.ui.graphics.painter.BitmapPainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import java.awt.Dimension
import java.io.File
import java.nio.file.Path
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class DesktopFilePicker : FilePicker {

    override fun hasPermission(): Boolean = true

    override suspend fun requestPermission(): Boolean = true

    override suspend fun pickFiles(config: FilePicker.Config): List<FileInfo> = withContext(Dispatchers.Default) {
        val fileChooser = JFileChooser().apply {
            isMultiSelectionEnabled = config.count > 1
            val allTypes = config.type.contains(FileType.ALL)
            val extensions = if (allTypes) {
                FileType.entries.filter { it != FileType.ALL }.flatMap { it.getExtensions() }
            } else {
                config.type.flatMap { it.getExtensions() }
            }.toTypedArray()
            val description = if (allTypes) "All Files" else config.type.joinToString(" or ") { it.name.lowercase() }
            fileFilter = if (extensions.isNotEmpty()) {
                FileNameExtensionFilter("$description (${extensions.joinToString()})", *extensions)
            } else {
                null
            }
            preferredSize = Dimension(800, 600)
        }

        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val files = if (config.count > 1) fileChooser.selectedFiles.toList() else listOf(fileChooser.selectedFile)
            files.take(config.count).map { file ->
                FileInfo(
                    path = file.absolutePath,
                    name = file.name,
                    size = file.length(),
                    mimeType = getMimeType(file, config.type)
                )
            }
        } else {
            emptyList()
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getBitMap(uri: String): BitmapPainter = withContext(Dispatchers.IO) {
        val file = File(uri)
        if (!file.exists()) throw IllegalArgumentException("File not found: $uri")
        val bitmap = file.inputStream().use { it.readAllBytes().decodeToImageBitmap() }
        BitmapPainter(bitmap)
    }

    override suspend fun getBytes(uri: String): ByteArray = withContext(Dispatchers.IO) {
        val file = File(uri)
        if (!file.exists()) throw IllegalArgumentException("File not found: $uri")
        file.inputStream().use { it.readAllBytes() }
    }

    private fun getMimeType(file: File, allowedFileTypes: List<FileType>): String {
        val extension = file.extension.lowercase()
        val matchingType = allowedFileTypes.find { it.getExtensions().contains(extension) }
        return matchingType?.toMimeType() ?: FileType.ALL.toMimeType() // Fallback to "*/*" if no match
    }

    private val Path.extension: String
        get() = fileName.toString().substringAfterLast('.', "").lowercase()

    private val File.extension: String
        get() = name.substringAfterLast('.', "").lowercase()
}