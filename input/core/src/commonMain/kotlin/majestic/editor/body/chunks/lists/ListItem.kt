package majestic.editor.body.chunks.lists

interface ListItem {
    var text: String
}

data class SimpleListItem(override var text: String) : ListItem