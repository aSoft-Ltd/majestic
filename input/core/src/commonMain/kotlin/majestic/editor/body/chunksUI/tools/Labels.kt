package majestic.editor.body.chunksUI.tools

import majestic.editor.body.chunks.Heading

class Labels(
    private val heading1: String,
    private val heading2: String,
    private val heading3: String,
    private val orderedChunk: String,
    private val unorderedChunk: String,
    val paragraph: String,
    val instructions: String,
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