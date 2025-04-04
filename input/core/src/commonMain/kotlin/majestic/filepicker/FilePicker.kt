package majestic.filepicker

interface FilePicker : FileOperations {
    data class Config(
        val type: List<FileType> = listOf(FileType.ALL),
        val count: Int = 1
    )

    fun hasPermission(): Boolean

    suspend fun requestPermission(): Boolean

    suspend fun pickFiles(config: Config = Config()): List<FileInfo>

}