package majestic.shared.users.label.table

data class FilterLabels(
    val search: String,
    val date: String,
    val to: String,
    val letterRange: String,
    val from: String,
    val ascend: String,
    val descend: String,
    val status: String,
    val cancel: String,
    val apply: String
)