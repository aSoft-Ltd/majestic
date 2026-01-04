package majestic.users.labels.table

data class TableLabels(
    val head: UsersTableHeaderLabels,
    val body: UsersTableBodyLabels
) {
    companion object {
        val english by lazy {
            TableLabels(
                head = UsersTableHeaderLabels.english,
                body = UsersTableBodyLabels.english
            )
        }
        val swahili by lazy {
            TableLabels(
                head = UsersTableHeaderLabels.swahili,
                body = UsersTableBodyLabels.swahili
            )
        }
    }
}