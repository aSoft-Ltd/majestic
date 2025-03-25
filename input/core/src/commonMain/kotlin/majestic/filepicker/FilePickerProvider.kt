package majestic.filepicker

object FilePickerProvider {
    private var filePicker: FilePicker? = null


    fun provideFilePicker(): FilePicker {
        return filePicker ?: throw IllegalStateException("FilePicker not initialized")
    }

    fun initialize(filePicker: FilePicker) {
        this.filePicker = filePicker
    }
}