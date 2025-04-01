package majestic.editor.body.chunks.lists

import androidx.compose.runtime.snapshots.SnapshotStateList
import majestic.editor.body.chunks.Chunk

interface ListChunk {
    val items: SnapshotStateList<ListItem>
    fun copyList(uid: Int): Chunk
}