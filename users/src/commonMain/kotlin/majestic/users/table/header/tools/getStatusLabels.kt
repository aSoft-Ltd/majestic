package majestic.users.table.header.tools

import majestic.users.tools.data.UsersStatusLabels

data class TableStatus(
    val invited: String,
    val active: String,
    val declined: String,
    val revoked: String
)

fun getStatusLabels(labels: TableStatus): UsersStatusLabels = UsersStatusLabels(
    invited = labels.invited,
    active = labels.active,
    declined = labels.declined,
    revoked = labels.revoked
)