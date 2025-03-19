package majestic.editor.body.chunksUI

import majestic.editor.body.chunks.Heading

class Labels(
    private val heading1: String,
    private val heading2: String,
    private val heading3: String,
    val paragraph: String,
    val bullet: String,
    val numbering: String
) {
    fun getHeadingHint(heading: Heading): String? {
        return when (heading.level) {
            1 -> heading1
            2 -> heading2
            3 -> heading3
            else -> null
        }
    }
}