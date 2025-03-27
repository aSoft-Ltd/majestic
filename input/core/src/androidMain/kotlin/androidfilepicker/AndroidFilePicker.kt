package androidfilepicker

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import majestic.filepicker.FileInfo
import majestic.filepicker.FilePicker
import majestic.filepicker.FileType
import java.io.File

class AndroidFilePicker(
    private val activity: ComponentActivity
) : FilePicker, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {
    private class Launcher(
        val permission: ActivityResultLauncher<Array<String>>,
        val picker: ActivityResultLauncher<Array<String>>,
    )

    private class Result(
        val permission: Channel<Map<String, Boolean>>,
        val picker: Channel<List<Uri>>,
    )

    private var launcher: Launcher? = null
    private val results by lazy {
        Result(
            Channel(Channel.CONFLATED),
            Channel(Channel.CONFLATED)
        )
    }
    private val scope = CoroutineScope(SupervisorJob())
    fun initilize() {
        launcher = Launcher(
            permission = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                scope.launch { results.permission.send(result) }
            },
            picker = activity.registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
                scope.launch { results.picker.send(uris) }
            }
        )
    }

    private fun getPermissionsForFileTypes(fileTypes: List<FileType>): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissions = mutableSetOf<String>()
            fileTypes.forEach { type ->
                when (type) {
                    FileType.IMAGE -> permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
                    FileType.VIDEO -> permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
                    FileType.AUDIO -> permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
                    FileType.ALL -> {
                        permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
                        permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
                        permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
                        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE) // Kept for legacy non-media access; consider SAF for Android 13+
                    }

                    FileType.DOCUMENT, FileType.PDF, FileType.TEXT -> {
                        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE) // Removed MANAGE_DOCUMENTS (invalid); SAF or MediaStore recommended
                    }
                }
            }
            permissions.toTypedArray()
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun hasPermission(): Boolean {
        return hasPermissionForTypes(listOf(FileType.ALL))
    }

    private fun hasPermissionForTypes(fileTypes: List<FileType>): Boolean {
        val permissions = getPermissionsForFileTypes(fileTypes)
        return permissions.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override suspend fun requestPermission(): Boolean {
        return requestPermissionForTypes(listOf(FileType.ALL))
    }

    private suspend fun requestPermissionForTypes(fileTypes: List<FileType>): Boolean {
        val permissions = getPermissionsForFileTypes(fileTypes)
        if (hasPermissionForTypes(fileTypes)) {
            return true
        }
        launcher?.permission?.launch(permissions) ?: return false
        val results = results.permission.receive()
        return results.values.all { it }
    }

    override suspend fun pickFiles(config: FilePicker.Config): List<FileInfo> {
        if (!hasPermissionForTypes(config.allowedFileTypes)) {
            val granted = requestPermissionForTypes(config.allowedFileTypes)
            if (!granted) {
                return kollections.emptyList()
            }
        }
        launcher?.picker?.launch(config.allowedFileTypes.map { it.toMimeType() }.toTypedArray()) ?: return emptyList()
        val uris = results.picker.receive()
        return processUris(uris)
    }

    private fun processUris(uris: List<Uri>): List<FileInfo> {
        return uris.mapNotNull { uri ->
            try {
                activity.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val nameIndex = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                        val sizeIndex = cursor.getColumnIndexOrThrow(OpenableColumns.SIZE)
                        val name = cursor.getString(nameIndex)
                        val size = cursor.getLong(sizeIndex)
                        FileInfo(uri.toString(), name, size, activity.contentResolver.getType(uri) ?: "*/*")
                    } else null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun searchFiles(
        query: String,
        path: String?, // Consider implementing path filtering if needed
        fileTypes: List<FileType>
    ): List<FileInfo> {
        if (!hasPermissionForTypes(fileTypes)) {
            val granted = requestPermissionForTypes(fileTypes)
            if (!granted) {
                return kollections.emptyList()
            }
        }
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.MIME_TYPE
        )
        val mimeTypes = fileTypes.map { it.toMimeType() }
        val selection = "${MediaStore.Files.FileColumns.DISPLAY_NAME} LIKE ?" +
                if (mimeTypes.isNotEmpty()) " AND ${MediaStore.Files.FileColumns.MIME_TYPE} IN (${mimeTypes.joinToString { "'$it'" }})" else ""
        val selectionArgs = arrayOf("%$query%")
        val cursor = activity.contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            projection,
            selection,
            selectionArgs,
            null
        )
        return cursor?.use { c ->
            val idColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val nameColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val sizeColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
            val mimeTypeColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
            val files = mutableListOf<FileInfo>()
            while (c.moveToNext()) {
                val id = c.getLong(idColumn)
                val name = c.getString(nameColumn)
                val size = c.getLong(sizeColumn)
                val mimeType = c.getString(mimeTypeColumn)
                files.add(FileInfo("content://media/external/files/$id", name, size, mimeType))
            }
            files
        } ?: kollections.emptyList()
    }

    override fun fileExists(path: String): Boolean {
        return if (path.startsWith("content://")) {
            try {
                activity.contentResolver.query(Uri.parse(path), null, null, null, null)?.use { it.moveToFirst() } ?: false
            } catch (e: Exception) {
                false
            }
        } else {
            File(path).exists()
        }
    }

    override suspend fun getBitMap(uri: String): BitmapPainter {
        val bitmap = try {
            if (uri.startsWith("content")) {
                activity.contentResolver.openInputStream(Uri.parse(uri))?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                } ?: throw IllegalArgumentException("Unable to open input stream for URI: $uri")
            } else {
                BitmapFactory.decodeFile(uri) ?: throw IllegalArgumentException("Unable to decode file: $uri")
            }
        } catch (e: Exception) {
            throw IllegalStateException("Failed to decode bitmap from $uri", e)
        }
        return BitmapPainter(bitmap.asImageBitmap())
    }

    override suspend fun getBytes(uri: String): ByteArray {
        return withContext(Dispatchers.IO) {
            try {
                activity.contentResolver.openInputStream(Uri.parse(uri))?.use { inputStream ->
                    inputStream.readBytes()
                } ?: throw IllegalArgumentException("Unable to open input stream for URI: $uri")
            } catch (e: Exception) {
                throw IllegalStateException("Failed to read bytes from $uri", e)
            }
        }
    }

    fun unregister() {
        launcher?.permission?.unregister()
        launcher?.picker?.unregister()
        scope.cancel()
        launcher = null
    }
}