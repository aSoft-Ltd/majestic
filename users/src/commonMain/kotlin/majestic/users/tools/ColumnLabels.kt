package majestic.users.tools

import majestic.users.table.header.tools.FiltersLabels

data class ColumnLabels(
    val checkbox: String,
    val name: String,
    val email: String,
    val id: String,
    val dateJoined: String,
    val lastActive: String,
    val roles: String,
    val permission: String,
    val status: String,
    val users: String,
    val filter: FiltersLabels
)