package majestic.users.labels.table

data class UsersTableHeaderLabels(
    val users: String,
    val name: String,
    val email: String,
    val selected: String,
    val id: String,
    val dateJoined: String,
    val lastActive: String,
    val roles: String,
    val permission: String,
    val status: String,
    val export: String,
    val actions: majestic.users.labels.table.UserSelectActionLabels,
    val filters: majestic.users.labels.table.UserFilterLabels
)