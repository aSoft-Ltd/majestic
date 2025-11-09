package majestic.users.tools

data class MenuActionLabels(
    val view: String,
    val block: String,
    val reset: String,
    val delete: String
)

enum class MenuAction {
    View,
    Block,
    Reset,
    Delete;

    fun getLabel(labels: MenuActionLabels): String {
        return when (this) {
            View -> labels.view
            Block -> labels.block
            Reset -> labels.reset
            Delete -> labels.delete
        }
    }
}
