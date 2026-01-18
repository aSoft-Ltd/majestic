package majestic.users.labels.table

data class UserFilterLabels(
    val search: String,
    val date: String,
    val to: String,
    val letterRange: String,
    val from: String,
    val ascend: String,
    val descend: String,
    val status: majestic.users.labels.table.UsersStatusLabels,
    val cancel: String,
    val apply: String
)