package majestic

import kiota.File
import kiota.FileManager
import kiota.toImageBitmap

class KiotaTest {
    suspend fun FileManager.convertToImage(file: File) {
        toImageBitmap(file)
    }
}