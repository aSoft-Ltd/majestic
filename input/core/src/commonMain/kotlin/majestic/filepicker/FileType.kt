package majestic.filepicker

enum class FileType {
    ALL, IMAGE, VIDEO, AUDIO, DOCUMENT, PDF, TEXT;

    fun toMimeType(): String = when (this) {
        ALL -> "*/*"
        IMAGE -> "image/*"
        VIDEO -> "video/*"
        AUDIO -> "audio/*"
        DOCUMENT -> "application/*"
        PDF -> "application/pdf"
        TEXT -> "text/*"
    }

    fun getExtensions(): List<String> = when (this) {
        IMAGE -> listOf("jpg", "jpeg", "png", "gif")
        VIDEO -> listOf("mp4", "mkv", "avi")
        AUDIO -> listOf("mp3", "wav", "ogg")
        DOCUMENT -> listOf("doc", "docx", "xls", "xlsx")
        PDF -> listOf("pdf")
        TEXT -> listOf("txt")
        ALL -> emptyList()
    }
}