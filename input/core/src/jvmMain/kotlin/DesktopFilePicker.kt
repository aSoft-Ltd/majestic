package majestic.filepicker

import androidx.compose.ui.graphics.painter.BitmapPainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import java.awt.Dimension
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class DesktopFilePicker : FilePicker {

    override fun hasPermission(): Boolean = true

    override suspend fun requestPermission(): Boolean = true

    override suspend fun pickFiles(config: FilePicker.Config): List<FileInfo> = withContext(Dispatchers.IO) {
        val fileChooser = JFileChooser().apply {
            isMultiSelectionEnabled = config.maxFiles > 1
            val allTypes = config.allowedFileTypes.contains(FileType.ALL)
            val extensions = if (allTypes) {
                FileType.entries.filter { it != FileType.ALL }.flatMap { it.getExtensions() }
            } else {
                config.allowedFileTypes.flatMap { it.getExtensions() }
            }.toTypedArray()
            val description = if (allTypes) "All Files" else config.allowedFileTypes.joinToString(" or ") { it.name.lowercase() }
            fileFilter = if (extensions.isNotEmpty()) {
                FileNameExtensionFilter("$description (${extensions.joinToString()})", *extensions)
            } else {
                null
            }
            preferredSize = Dimension(800, 600)
        }

        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val files = if (config.maxFiles > 1) fileChooser.selectedFiles.toList() else listOf(fileChooser.selectedFile)
            files.take(config.maxFiles).map { file ->
                FileInfo(
                    path = file.absolutePath,
                    name = file.name,
                    size = file.length(),
                    mimeType = getMimeType(file, config.allowedFileTypes)
                )
            }
        } else {
            emptyList()
        }
    }

    override suspend fun searchFiles(
        query: String,
        path: String?,
        fileTypes: List<FileType>
    ): List<FileInfo> = withContext(Dispatchers.IO) {
        val basePath = Paths.get(path ?: System.getProperty("user.home"))
        val allTypes = fileTypes.contains(FileType.ALL)
        val allowedExtensions = if (allTypes) {
            FileType.entries.filter { it != FileType.ALL }.flatMap { it.getExtensions() }.toSet()
        } else {
            fileTypes.flatMap { it.getExtensions() }.toSet()
        }

        try {
            Files.walk(basePath)
                .filter { Files.isRegularFile(it) }
                .filter { file ->
                    val nameMatches = file.fileName.toString().contains(query, ignoreCase = true)
                    val typeMatches = allowedExtensions.isEmpty() || allowedExtensions.contains(file.extension.lowercase())
                    nameMatches && typeMatches
                }
                .map { file ->
                    FileInfo(
                        path = file.toAbsolutePath().toString(),
                        name = file.fileName.toString(),
                        size = Files.size(file),
                        mimeType = getMimeType(file.toFile(), fileTypes)
                    )
                }
                .toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun fileExists(path: String): Boolean = File(path).exists()

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
        return when (val extension = file.extension.lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "gif" -> "image/gif"
            "mp4" -> "video/mp4"
            "mkv" -> "video/x-matroska"
            "avi" -> "video/x-msvideo"
            "mp3" -> "audio/mpeg"
            "wav" -> "audio/wav"
            "ogg" -> "audio/ogg"
            "doc" -> "application/msword"
            "docx" -> "application/vnd.openxmlformats-office-document.wordprocessingml.document"
            "xls" -> "application/vnd.ms-excel"
            "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            "pdf" -> "application/pdf"
            "txt" -> "text/plain"
            else -> {
                // Fallback to FileType.toMimeType() for robustness
                val matchingType = allowedFileTypes.find { it.getExtensions().contains(extension) }
                matchingType?.toMimeType() ?: FileType.ALL.toMimeType() // "*/*" if no match
            }
        }
    }

    private val Path.extension: String
        get() = fileName.toString().substringAfterLast('.', "").lowercase()

    private val File.extension: String
        get() = name.substringAfterLast('.', "").lowercase()
}