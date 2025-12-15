package majestic.users.labels.table

data class UsersTableLabels(
    val head: UsersTableHeaderLabels,
    val body: UsersTableBodyLabels
) {
    companion object {
        val english by lazy {
            UsersTableLabels(
                head = UsersTableHeaderLabels.english,
                body = UsersTableBodyLabels.english
            )
        }
        val swahili by lazy {
            UsersTableLabels(
                head = UsersTableHeaderLabels.swahili,
                body = UsersTableBodyLabels.swahili
            )
        }
    }
}