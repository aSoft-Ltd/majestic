package majestic.filepicker

data class FileInfo(
    val path: String,
    val name: String,
    val size: Long,
    val mimeType: String
)